package com.yaoyili.model;

public class SheetCollectKey {
    private Integer uid;

    private Integer sid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public SheetCollectKey(Integer uid, Integer sid) {
        this.uid = uid;
        this.sid = sid;
    }
}