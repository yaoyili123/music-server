package com.yaoyili.controller.resbeans;

import com.yaoyili.model.Album;
import lombok.Data;

public class AlbumResponse extends Album {
    private String author;

    public AlbumResponse() { }

    public AlbumResponse(String author) {
        super();
        this.author = author;
    }

    public AlbumResponse(Album album, String author) {
        super(album.getId(), album.getArtistId(), album.getPicUrl(), album.getName(), album.getCompany(), album.getPlay());
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
