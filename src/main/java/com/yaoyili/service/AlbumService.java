package com.yaoyili.service;

import com.yaoyili.model.Album;

import java.util.List;

public interface AlbumService {

    int total();

    List<Album> getRank();

    void updateRank();

    List<Album> findbyName(String name, int limit);

    Album find(Long id);

    List<Album> find(int page, int size, String name, Long aid);

    List<Album> findByArtist(Long aid);

    List<Album> findCollections(Long uid);

    void add(Album album);

    void update(Album album);

    void del(Long id);

    void delByArtist(Long aid);

    void incr(Long id);

    void updatePlay();
}
