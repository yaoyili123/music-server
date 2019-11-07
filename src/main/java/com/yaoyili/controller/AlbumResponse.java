package com.yaoyili.controller;

import com.yaoyili.model.Album;
import lombok.Data;

@Data
public class AlbumResponse extends Album {
    private String author;

    public AlbumResponse() { }

    public AlbumResponse(String author) {
        super();
        this.author = author;
    }

    public AlbumResponse(Album album, String author) {
        super(album.getId(), album.getArtistId(), album.getPicUrl(), album.getName(), album.getCompany());
        this.author = author;
    }
}
