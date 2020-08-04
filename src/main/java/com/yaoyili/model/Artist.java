package com.yaoyili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private Long id;

    private String picUrl;

    private String name;

    private Integer albumSize;

    private Integer musicSize;

}