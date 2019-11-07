package com.yaoyili.model;

public class SheetSongKey {
    private Integer songid;

    private Integer sheetid;

    public SheetSongKey() {
    }

    public SheetSongKey(Integer songid, Integer sheetid) {
        this.songid = songid;
        this.sheetid = sheetid;
    }

    public Integer getSongid() {
        return songid;
    }

    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    public Integer getSheetid() {
        return sheetid;
    }

    public void setSheetid(Integer sheetid) {
        this.sheetid = sheetid;
    }
}