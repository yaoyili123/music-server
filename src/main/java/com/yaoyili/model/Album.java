package com.yaoyili.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album{
    private Long id;

    private Long artistId;

    private String name;

    private String author;

    private String company;

    private String picUrl;

    private Long play;
}