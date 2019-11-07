package com.yaoyili.controller;

import com.yaoyili.model.AccountAuth;
import com.yaoyili.model.Sheet;
import com.yaoyili.service.MainService;
import com.yaoyili.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "register")
    public ResultBean register(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "nickname", required = false, defaultValue = "无名") String nickname,
            @RequestParam(value = "pic", required = false) MultipartFile pic
    ) {
//        logger.info("username=" + username + "password=" + password + "nickname=" + nickname + "pic=" + pic.getOriginalFilename())
        userService.register(username, password, nickname, pic);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "login")
    public ResultBean register(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password)
    {
        return new ResultBean<AccountAuth>(userService.login(username, password));
    }
}
