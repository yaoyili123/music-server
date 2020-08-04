package com.yaoyili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuth {
    private Long id;

    private String username;

    private String password;

    private String picUrl;

    private String nickname;

    private Long lid;

    public AccountAuth(String username, String password, String picUrl, String nickname, Long lid) {
        this.username = username;
        this.password = password;
        this.picUrl = picUrl;
        this.nickname = nickname;
        this.lid = lid;
    }
}