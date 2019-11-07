package com.yaoyili.service.impl;

import com.yaoyili.controller.AlbumResponse;
import com.yaoyili.dao.AlbumMapper;
import com.yaoyili.dao.ArtistMapper;
import com.yaoyili.model.Album;
import com.yaoyili.model.Artist;
import com.yaoyili.service.AlbumService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ArtistMapper artistMapper;

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

}
