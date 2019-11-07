package com.yaoyili.model;

public class Song {
    private Integer id;

    private Integer albumId;

    private String name;

    private String mUrl;

    public Song() {}

    public Song(Integer id, Integer albumId, String name, String mUrl) {
        this.id = id;
        this.albumId = albumId;
        this.name = name;
        this.mUrl = mUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumid) {
        this.albumId = albumid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}