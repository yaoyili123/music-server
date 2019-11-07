package com.yaoyili.controller;

import com.yaoyili.model.Song;
import lombok.Data;

@Data
public class SongResponse extends Song{

    private String albumName;

    private String albumUrl;

    private Integer artistId;

    private String author;

    public SongResponse() {}

    public SongResponse(String albumName, String albumUrl, Integer artistId, String author) {
        super();
        this.albumName = albumName;
        this.albumUrl = albumUrl;
        this.artistId = artistId;
        this.author = author;
    }

    public SongResponse(Song song, String albumName, String albumUrl, Integer artistId, String author) {
        super(song.getId(), song.getAlbumId(), song.getName(), song.getmUrl());
        this.albumName = albumName;
        this.albumUrl = albumUrl;
        this.artistId = artistId;
        this.author = author;
    }

    public SongResponse(Integer id, Integer albumid, String name, String mUrl, String albumName, String albumUrl, Integer artistId, String author) {
        super(id, albumid, name, mUrl);
        this.albumName = albumName;
        this.albumUrl = albumUrl;
        this.artistId = artistId;
        this.author = author;
    }
}
