package com.yaoyili.service.impl;

import com.yaoyili.CheckException;
import com.yaoyili.dao.AlbumMapper;
import com.yaoyili.dao.ArtistCollectMapper;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.dao.SongMapper;
import com.yaoyili.model.Artist;
import com.yaoyili.service.AlbumService;
import com.yaoyili.service.ArtistService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.yaoyili.utils.CheckUtils.*;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private ArtistCollectMapper artistCollectMapper;

    @Autowired
    private AlbumService albumService;

    @Override
    public int total() {
        return artistMapper.total();
    }

    @Override
    public List<Artist> findbyName(String name, int limit) {
        List<Artist> artists = artistMapper.selectByName(name);
        if (limit == -1 || limit > artists.size())
            return artists;
        else
            return artists.subList(0, limit);
    }

    @Override
    public List<Artist> findCollections(Long uid) {
        return artistMapper.selectCollections(uid);
    }

    @Override
    public Artist find(Long id) {
        if (id < 0) {
            throw new CheckException("id不能为负数");
        }
        Artist artist = artistMapper.select(id);
        if (artist == null) {
            throw new CheckException("找不到数据");
        }
        return artist;
    }

    @Override
    public List<Artist> find(int page, int size, String name) {
        checkPageParams(page, size);
        if (page == -1)
            return artistMapper.selectAll(-1, -1, name);
        else {
            int offset = (page - 1) * size;
            return artistMapper.selectAll(offset, size, name);
        }
    }

    @Override
    public void add(Artist artist) {

        notNull(artist, "数据非法");
        notEmpty(artist.getName(), "名称不能为空");
        check(artist.getName().length() <= 100, "名称长度不能超过100");

        if(artist.getPicUrl().isEmpty() || artist.getPicUrl() == null)
            artist.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_FILENAME);
        artist.setAlbumSize(0);
        artist.setMusicSize(0);
        artistMapper.insert(artist);
    }

    @Override
    public void update(Artist artist) {

        notNull(artist, "数据非法");
        notEmpty(artist.getName(), "名称不能为空");
        check(artist.getName().length() <= 100, "名称长度不能超过100");

        if(artist.getPicUrl().isEmpty() || artist.getPicUrl() == null)
            artist.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        artistMapper.update(artist);
    }

    @Override
    @Transactional
    public void del(Long id) {
        artistMapper.delete(id);
        artistCollectMapper.deleteByArtist(id);
        albumService.delByArtist(id);
    }
}
