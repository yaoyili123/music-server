package com.yaoyili.controller;

import com.yaoyili.controller.ao.ResultBean;
import com.yaoyili.model.AccountAuth;
import com.yaoyili.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/info")
    public ResultBean getUserInfo(
            @RequestParam(value = "uid") Long uid) {
        return new ResultBean<AccountAuth>(userService.getUserInfo(uid));
    }

    @PostMapping(value = "/reg")
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

    @PostMapping(value = "/login")
    public ResultBean login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        return new ResultBean<AccountAuth>(userService.login(username, password));
    }

    @GetMapping(value = "/checkCol")
    public ResultBean checkCollected(
            @RequestParam(value = "uid") Long uid,
            @RequestParam(value = "rid") Long rid,
            @RequestParam(value = "t") int t) {

        return new ResultBean<Boolean>(userService.checkCollected(uid, rid, t));
    }

    @GetMapping(value = "/haveSheet")
    public ResultBean checkHavedSheet(
            @RequestParam(value = "uid") Long uid,
            @RequestParam(value = "sid") Long sid){

        return new ResultBean<Boolean>(userService.checkHavedSheet(uid, sid));
    }

    @PostMapping(value = "/col")
    public ResultBean collect(
            @RequestParam(value = "uid") Long uid,
            @RequestParam(value = "rid") Long rid,
            @RequestParam(value = "t") int t) {
        userService.collect(uid, rid, t);
        return new ResultBean<Map>(new HashMap());
    }

    @PostMapping(value = "/uncol")
    public ResultBean uncollect(
            @RequestParam(value = "uid") Long uid,
            @RequestParam(value = "rid") Long rid,
            @RequestParam(value = "t") int t) {
        userService.uncollect(uid, rid, t);
        return new ResultBean<Map>(new HashMap());
    }
}
