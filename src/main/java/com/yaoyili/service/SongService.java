package com.yaoyili.service;

import com.yaoyili.controller.ao.SongLyric;
import com.yaoyili.model.Song;

import java.util.List;

public interface SongService {

    int total();

    List<Song> findbyName(String name, int limit);

    List<Song> find(int page, int size, String name, Long aid);

    List<Song> find(Long aid, String type);

    Song find(Long id);

    void add(SongLyric song);

    void update(SongLyric song);

    void del(Long id);

    void delByAlbum(Long aid);

    String findLyric(Long id);
}
