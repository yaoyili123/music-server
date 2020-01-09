package com.yaoyili.controller.resbeans;

import com.yaoyili.model.Song;
import lombok.Data;

public class SongLyric extends Song {

    private String lyric;

    public SongLyric() {}

    public SongLyric(Song song, String lyric) {
        super(song.getId(), song.getAlbumId(), song.getName(), song.getmUrl());
        this.lyric = lyric;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
