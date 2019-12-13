package com.yaoyili.service.impl;

import com.yaoyili.controller.CheckException;
import com.yaoyili.dao.*;
import com.yaoyili.model.*;
import com.yaoyili.service.*;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountAuthMapper accountAuthMapper;

    @Autowired
    private SheetMapper sheetMapper;

    @Autowired
    private MainService mainService;

    @Autowired
    private AlbumCollectMapper albumCollectMapper;

    @Autowired
    private SheetCollectMapper sheetCollectMapper;

    @Autowired
    private ArtistCollectMapper artistCollectMapper;


    @Override
    public void register(String username, String password, String nickname, MultipartFile file) {

        if (accountAuthMapper.checkRepeated(username) != null)
            throw new CheckException("用户名已存在");
        String filename = Global.HOST_NAME;
        if (file == null)
            filename += '/' + Global.DEAFAULT_FILENAME;
        else {
            filename += '/' + file.getOriginalFilename();
            mainService.savePicture(file);
        }
        AccountAuth accountAuth =  new AccountAuth(username, password, nickname, filename, -1);
        accountAuthMapper.insertSelective(accountAuth);
//        System.out.println("uid=" + uid);
        Sheet like = new Sheet();
        like.setUid(accountAuth.getId());
        like.setName("我喜欢的音乐");
        like.setSongNum(0);
        like.setS(false);
        like.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        sheetMapper.insertSelective(like);
        accountAuth.setLid(like.getId());
        accountAuthMapper.updateByPrimaryKeySelective(accountAuth);
    }

    @Override
    public AccountAuth login(String username, String password) {
        AccountAuth accountAuth = accountAuthMapper.checkUserAuth(username, password);
        if (accountAuth == null) {
            if (accountAuthMapper.checkRepeated(username) == null)
                throw new CheckException("用户名不存在");
            else
                throw new CheckException("密码不正确");
        }
        return accountAuth;
    }

    @Override
    public void collect(int uid, int rid, int t) {
        /*
         * t: 1.专辑， 2.歌单，3.歌手
         * */
        switch (t) {
            case 1: {
                albumCollectMapper.insertSelective(new AlbumCollectKey(uid, rid));
                break;
            }

            case 2: {
                Sheet sheet = sheetMapper.selectByPrimaryKey(rid);
                if (!sheet.getS())
                    throw new CheckException("不能收藏私有歌单");
                sheetCollectMapper.insertSelective(new SheetCollectKey(uid, rid));
                break;
            }

            case 3: {
                artistCollectMapper.insertSelective(new ArtistCollectKey(uid, rid));
                break;
            }

            default:
                throw new CheckException("系统错误");
        }
    }

    @Override
    public void uncollect(int uid, int rid, int t) {
        /*
         * t: 1.专辑， 2.歌单，3.歌手
         * */
        switch (t) {
            case 1: {
                albumCollectMapper.deleteByPrimaryKey(new AlbumCollectKey(uid, rid));
                break;
            }

            case 2: {
                sheetCollectMapper.deleteByPrimaryKey(new SheetCollectKey(uid, rid));
                break;
            }

            case 3: {
                artistCollectMapper.deleteByPrimaryKey(new ArtistCollectKey(uid, rid));
                break;
            }

            default:
                throw new CheckException("系统错误");
        }
    }

    @Override
    public Boolean checkCollected(int uid, int id, int t) {
        boolean res = false;
        /*
         * t: 1.专辑， 2.歌单，3.歌手
         * */
        switch (t) {
            case 1: {
                if (albumCollectMapper.check(new AlbumCollectKey(uid, id)) != null)
                    res = true;
                break;
            }

            case 2: {
                if (sheetCollectMapper.check(new SheetCollectKey(uid, id)) != null)
                    res = true;
                break;
            }

            case 3: {
                if (artistCollectMapper.check(new ArtistCollectKey(uid, id)) != null)
                    res = true;
                break;
            }

            default:
                throw new CheckException("系统错误");
        }
        return res;
    }

    @Override
    public Boolean checkHavedSheet(int uid, int sid) {
        return sheetMapper.selectByPrimaryKey(sid).getUid() == uid ? true : false;
    }
}
