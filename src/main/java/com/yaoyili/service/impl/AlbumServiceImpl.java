package com.yaoyili.service.impl;

import com.yaoyili.dao.AlbumCollectMapper;
import com.yaoyili.dao.AlbumMapper;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.model.Album;
import com.yaoyili.model.Artist;
import com.yaoyili.service.AlbumService;
import com.yaoyili.service.SongService;
import com.yaoyili.utils.Global;
import com.yaoyili.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.yaoyili.utils.CheckUtils.*;
import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumCollectMapper albumCollectMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public int total() {
        return albumMapper.total();
    }

    @Override
    public Album find(Long id) {
        return albumMapper.select(id);
    }

    @Override
    public List<Album> find(int page, int size, String name, Long aid) {
        checkPageParams(page, size);
        if (page == -1)
            return albumMapper.selectAll(-1, -1, name, aid);
        else {
            int offset = (page - 1) * size;
            return albumMapper.selectAll(offset, size, name, aid);
        }
    }

    @Override
    public List<Album> findByArtist(Long aid) {
        return albumMapper.selectByAid(aid);
    }

    @Override
    public List<Album> findbyName(String name, int limit) {
        List<Album> responses = albumMapper.selectByName(name);

        if (limit == -1 || limit > responses.size())
            return responses;
        else
            return responses.subList(0, limit);
    }

    @Override
    public List<Album> findCollections(Long uid) {
        return albumMapper.selectCollections(uid);
    }

    @Override
    public void add(Album album) {
        notNull(album, "未知错误");
        notEmpty(album.getName(), "专辑名称不能为空");
        notEmpty(album.getAuthor(), "作者名为空！");
        check(album.getArtistId() > 0, "作者ID非法！");
        check(album.getName().length() <= 100, "名称长度不能超过100");

        if(album.getPicUrl().isEmpty() || album.getPicUrl() == null)
            album.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        albumMapper.insert(album);
    }

    @Override
    public void update(Album album) {

        notNull(album, "未知错误");
        notEmpty(album.getName(), "专辑名称不能为空");
        notEmpty(album.getAuthor(), "作者名为空！");
        check(album.getArtistId() > 0, "作者ID非法！");
        check(album.getName().length() <= 100, "名称长度不能超过100");

        albumMapper.update(album);
    }

    @Override
    @Transactional
    public void del(Long id) {
        albumMapper.delete(id);
        albumCollectMapper.deleteByAlbum(id);
        songService.delByAlbum(id);
    }

    @Override
    @Transactional
    public void delByArtist(Long aid) {
        List<Album> artists = findByArtist(aid);
        for (Album item : artists) {
            del(item.getId());
        }
    }

    @Override
    public List<Album> getRank() {
        List<Album> albums = null;
        String rKey = "album:rank";
        if (!redisUtils.hasKey(rKey)) {
            albums = albumMapper.selectRank(Global.RANK_LENGTH);
            Set<ZSetOperations.TypedTuple<Object>> tuples
                    = new HashSet<ZSetOperations.TypedTuple<Object>>();
            for (Album album : albums) {
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
        List<Album> albums = albumMapper.selectRank(Global.RANK_LENGTH);
        redisUtils.del(rKey);
        Set<ZSetOperations.TypedTuple<Object>> tuples
                = new HashSet<ZSetOperations.TypedTuple<Object>>();
        for (Album album : albums) {
            ZSetOperations.TypedTuple<Object> objectTypedTuple
                    = new DefaultTypedTuple<Object>(album, (double)album.getPlay());
            tuples.add(objectTypedTuple);
        }
        redisUtils.zadds(rKey, tuples);
    }

    @Override
    public void incr(Long id) {
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
