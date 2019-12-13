package com.yaoyili.service.impl;

import com.yaoyili.controller.CheckException;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.model.Artist;
import com.yaoyili.service.ArtistService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public int total() {
        return artistMapper.total();
    }

    @Override
    public List<Artist> findArtistbyName(String name, int limit) {
        List<Artist> artists = artistMapper.selectByName(name);
        if (limit == -1 || limit > artists.size())
            return artists;
        else
            return artists.subList(0, limit);
    }

    @Override
    public List<Artist> findCollections(int uid) {
        return artistMapper.selectCollections(uid);
    }

    @Override
    public Artist findAritist(int id) {
        if (id < 0) {
            throw new CheckException("id不能为负数");
        }
        Artist artist = artistMapper.selectByPrimaryKey(id);
        if (artist == null) {
            throw new CheckException("找不到数据");
        }
        return artist;
    }

    @Override
    public List<Artist> findArtistsAll(int page, int size, String name) {
        Global.checkPageParams(page, size);
        if (page == -1)
            return artistMapper.selectAll(-1, -1, name);
        else {
            int offset = (page - 1) * size;
            return artistMapper.selectAll(offset, size, name);
        }
    }

    @Override
    public void addArtist(Artist artist) {
        if(artist.getPicUrl().isEmpty() || artist.getPicUrl() == null)
            artist.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_FILENAME);
        artist.setAlbumSize(0);
        artist.setMusicSize(0);
        artistMapper.insertSelective(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        if(artist.getPicUrl().isEmpty() || artist.getPicUrl() == null)
            artist.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        artistMapper.updateByPrimaryKeySelective(artist);
    }

    @Override
    public void deleteArtist(int id) {
        artistMapper.deleteByPrimaryKey(id);
    }
}
