package com.yaoyili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    private Long id;

    private Long albumId;

    private String albumName;

    private String musicUrl;

    private String albumUrl;

    private Long artistId;

    private String author;

    private String name;
}