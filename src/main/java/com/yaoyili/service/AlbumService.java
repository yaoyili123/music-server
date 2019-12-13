package com.yaoyili.service;

import com.yaoyili.controller.resbeans.AlbumResponse;
import com.yaoyili.model.Album;

import java.util.List;

public interface AlbumService {

    int total();

    List<AlbumResponse> findAlbumbyName(String name, int limit);

    AlbumResponse findAlbum(int id);

    List<AlbumResponse> findAlbums(int page, int size, String name, int aid);

    List<AlbumResponse> findAlbumsByArtist(int aid);

    List<AlbumResponse> findCollections(int uid);

    void addAlbum(Album album);

    void updateAlbum(Album album);

    void delAlbum(Integer id);
}
