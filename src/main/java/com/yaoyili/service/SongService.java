package com.yaoyili.service;

import com.yaoyili.controller.SongResponse;
import com.yaoyili.model.Song;

import java.util.List;

public interface SongService {

    int total();

    List<SongResponse> findSongbyName(String name, int limit);

    List<SongResponse> findSongsAll(int page, int size, String name, int aid);

    List<SongResponse> findSongs(int aid, String type);

    void addSong(Song song);

    void updateSong(Song song);

    void deleteSong(int id);
}
