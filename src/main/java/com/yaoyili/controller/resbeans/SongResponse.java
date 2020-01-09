package com.yaoyili.controller.resbeans;

import com.yaoyili.model.Song;
import lombok.Data;

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

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
