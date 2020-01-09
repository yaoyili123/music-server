package com.yaoyili.service.impl;

import com.yaoyili.controller.resbeans.AlbumResponse;
import com.yaoyili.dao.AlbumMapper;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.model.Album;
import com.yaoyili.model.Artist;
import com.yaoyili.service.AlbumService;
import com.yaoyili.utils.Global;
import com.yaoyili.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public int total() {
        return albumMapper.total();
    }

    @Override
    public AlbumResponse findAlbum(int id) {
        List<Album> albums = new ArrayList<>();
        albums.add(albumMapper.selectByPrimaryKey(id));
        return albumsConvert(albums).get(0);
    }

    @Override
    public List<AlbumResponse> findAlbums(int page, int size, String name, int aid) {
        Global.checkPageParams(page, size);
        if (page == -1)
            return albumsConvert(albumMapper.selectAll(-1, -1, name, aid));
        else {
            int offset = (page - 1) * size;
            return albumsConvert(albumMapper.selectAll(offset, size, name, aid));
        }
    }

    @Override
    public List<AlbumResponse> findAlbumsByArtist(int aid) {
        return albumsConvert(albumMapper.selectByAid(aid));
    }

    @Override
    public List<AlbumResponse> findAlbumbyName(String name, int limit) {
        List<AlbumResponse> responses = albumsConvert(albumMapper.selectByName(name));

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    public List<AlbumResponse> findCollections(int uid) {
        return albumsConvert(albumMapper.selectCollections(uid));
    }

    @Override
    public void addAlbum(Album album) {
        if(album.getPicUrl().isEmpty() || album.getPicUrl() == null)
            album.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        albumMapper.insertSelective(album);
    }

    @Override
    public void updateAlbum(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void delAlbum(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    private List<AlbumResponse> albumsConvert(List<Album> albums) {
        List<AlbumResponse> responses = new ArrayList<>();
        for(Album album: albums) {
            Artist artist = artistMapper.selectByPrimaryKey(album.getArtistId());
            AlbumResponse response = new AlbumResponse(album, artist.getName());
            responses.add(response);
        }

        return responses;
    }

    @Override
    public List<AlbumResponse> getRank() {
        List<AlbumResponse> albums = null;
        String rKey = "album:rank";
        if (!redisUtils.hasKey(rKey)) {
            albums = albumsConvert(albumMapper.selectRank(Global.RANK_LENGTH));
            Set<ZSetOperations.TypedTuple<Object>> tuples
                    = new HashSet<ZSetOperations.TypedTuple<Object>>();
            for (AlbumResponse album : albums) {
                ZSetOperations.TypedTuple<Object> objectTypedTuple
                        = new DefaultTypedTuple<Object>(album, (double)album.getPlay());
                tuples.add(objectTypedTuple);
            }
            long len = redisUtils.zadds(rKey, tuples);
            Collections.sort(albums, (s1, s2) -> s2.getPlay().compareTo(s1.getPlay()));
        }
        else {
            albums = new ArrayList<>(redisUtils.zrevrange(rKey, 0, Global.RANK_LENGTH));
        }
        return albums;
    }

    @Override
    public void updateRank() {
        String rKey = "album:rank";
        List<AlbumResponse> albums = albumsConvert(albumMapper.selectRank(Global.RANK_LENGTH));
        redisUtils.del(rKey);
        Set<ZSetOperations.TypedTuple<Object>> tuples
                = new HashSet<ZSetOperations.TypedTuple<Object>>();
        for (AlbumResponse album : albums) {
            ZSetOperations.TypedTuple<Object> objectTypedTuple
                    = new DefaultTypedTuple<Object>(album, (double)album.getPlay());
            tuples.add(objectTypedTuple);
        }
        redisUtils.zadds(rKey, tuples);
    }

    @Override
    public void incr(Integer id) {
        String key = "album:" + id.toString() + ":incr";
        redisUtils.incr(key, 1);
    }

    @Override
    public void updatePlay() {
        Set<String> keys = redisUtils.keys("album:*:incr");
        List<Integer> values = redisUtils.mget(keys);
        int idx = 0;
        for (String key : keys) {
            int incr = values.get(idx++);
            int id = Integer.parseInt(key.substring(6, key.length()-5));
            albumMapper.updateIncr(id, incr);
        }
        redisUtils.del(keys);
    }
}
