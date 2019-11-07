package com.yaoyili.service;

import com.yaoyili.model.AccountAuth;
import com.yaoyili.model.Sheet;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    public void register(String username, String password, String nickname, MultipartFile file);

    public AccountAuth login(String username, String password);


}
