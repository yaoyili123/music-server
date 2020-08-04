package com.yaoyili.service;

import com.yaoyili.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findbyName(String name, int limit);

    List<Artist> find(int page, int size, String name);

    int total();

    Artist find(Long id);

    List<Artist> findCollections(Long uid);

    void add(Artist artist);

    void update(Artist artist);

    void del(Long id);
}
