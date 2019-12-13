package com.yaoyili.model;

import java.util.Date;

public class Sheet {
    private Integer id;

    private String name;

    private Integer uid;

    private Integer songNum;

    private String picUrl;

    private String description;

    private Boolean s = true;

    public Sheet() {}

    public Sheet(Integer id, String name, Integer uid, Integer songNum, String picUrl, String description) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.picUrl = picUrl;
        this.songNum = songNum;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongNum() {
        return songNum;
    }

    public void setSongNum(Integer songNum) {
        this.songNum = songNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picurl) {
        this.picUrl = picurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getS() {
        return s;
    }

    public void setS(Boolean show) {
        this.s = show;
    }
}