package com.yaoyili.service;

import com.yaoyili.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findArtistbyName(String name, int limit);

    List<Artist> findArtistsAll(int page, int size, String name);

    int total();

    Artist findAritist(int id);

    List<Artist> findCollections(int uid);

    void addArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(int id);
}
