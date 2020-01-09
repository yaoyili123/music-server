package com.yaoyili.model;

public class Album{
    private Integer id;

    private Integer artistId;

    private String picUrl;

    private String name;

    private String company;

    private Long play;

    public Album() {}

    public Album(Integer id, Integer artistId, String picUrl, String name, String company, Long play) {
        this.id = id;
        this.artistId = artistId;
        this.picUrl = picUrl;
        this.name = name;
        this.company = company;
        this.play = play;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistid) {
        this.artistId = artistid;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getPlay() {
        return play;
    }

    public void setPlay(Long play) {
        this.play = play;
    }
}