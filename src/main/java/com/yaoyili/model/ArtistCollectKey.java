package com.yaoyili.model;

public class ArtistCollectKey {
    private Integer uid;

    private Integer aid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public ArtistCollectKey(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
    }
}