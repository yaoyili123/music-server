package com.yaoyili.service;

import com.yaoyili.controller.resbeans.SongLyric;
import com.yaoyili.controller.resbeans.SongResponse;
import com.yaoyili.model.Lyric;
import com.yaoyili.model.Song;

import java.util.List;

public interface SongService {

    int total();

    List<SongResponse> findSongbyName(String name, int limit);

    List<SongResponse> findSongsAll(int page, int size, String name, int aid);

    List<SongResponse> findSongs(int aid, String type);

    void addSong(SongLyric song);

    void updateSong(SongLyric song);

    void deleteSong(int id);

    String findLyric(int id);
}
