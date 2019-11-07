package com.yaoyili.model;

public class Artist {
    private Integer id;

    private String picUrl;

    private String name;

    private Integer albumSize;

    private Integer musicSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picurl) {
        this.picUrl = picurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlbumSize() {
        return albumSize;
    }

    public void setAlbumSize(Integer albumsize) {
        this.albumSize = albumsize;
    }

    public Integer getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(Integer musicsize) {
        this.musicSize = musicsize;
    }
}