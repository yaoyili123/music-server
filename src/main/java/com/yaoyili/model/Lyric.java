package com.yaoyili.model;

public class Lyric {

    private Integer sid;

    private String lyric;

    public Lyric() {}

    public Lyric(Integer sid, String lyric) {
        this.sid = sid;
        this.lyric = lyric;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}