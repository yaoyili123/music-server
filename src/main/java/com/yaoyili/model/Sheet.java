package com.yaoyili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sheet {

    private Long id;

    private Long uid;

    private String name;

    private String username;

    private Integer songNum;

    private String picUrl;

    private String description;

    private Boolean s = true;

    private Long play;
}