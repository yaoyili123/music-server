package com.yaoyili.model;

public class AccountAuth {
    private Integer id;

    private String username;

    private String password;

    private String picUrl;

    private String nickname;

    private Integer lid;

    public AccountAuth() { }

    public AccountAuth(String username, String password, String nickname, String picurl, Integer lid) {
        this.username = username;
        this.password = password;
        this.picUrl = picurl;
        this.nickname = nickname;
        this.lid = lid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picurl) {
        this.picUrl = picurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }
}