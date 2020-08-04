package com.yaoyili.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongLyric {

    private Long id;

    private Long albumId;

    private String albumName;

    private String albumUrl;

    private Long artistId;

    private String author;

    private String name;

    private String musicUrl;

    private String lyric;
}
