package com.yaoyili.service.impl;

import com.yaoyili.CheckException;
import com.yaoyili.dao.*;
import com.yaoyili.model.*;
import com.yaoyili.service.*;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import static com.yaoyili.utils.CheckUtils.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountAuthMapper accountAuthMapper;

    @Autowired
    private SheetMapper sheetMapper;

    @Autowired
    private MainService mainService;

    @Autowired
    private SheetService sheetService;

    @Autowired
    private AlbumCollectMapper albumCollectMapper;

    @Autowired
    private SheetCollectMapper sheetCollectMapper;

    @Autowired
    private ArtistCollectMapper artistCollectMapper;


    @Override
    @Transactional
    public void register(String username, String password, String nickname, MultipartFile file) {

        notEmpty(username, "用户名不能为空");
        notEmpty(password, "密码不能为空");
        notEmpty(nickname, "昵称不能为空");
        check(username.length() >= 6 && username.length() <= 16, "用户名长度必须为6-16位");
        check(password.length() >= 6 && password.length() <= 16, "密码长度必须为6-16位");
        check(nickname.length() <= 16, "昵称最长不能超过16位");

        if (accountAuthMapper.checkRepeated(username) != null)
            throw new CheckException("用户名已存在");
        String filename = Global.HOST_NAME;
        if (file == null)
            filename += '/' + Global.DEAFAULT_FILENAME;
        else {
            filename += '/' + file.getOriginalFilename();
            mainService.savePicture(file);
        }
        AccountAuth accountAuth =  new AccountAuth(username, password, filename, nickname, new Long(0));
        accountAuthMapper.insert(accountAuth);
//        System.out.println("uid=" + uid);
        Sheet like = new Sheet();
        like.setUid(accountAuth.getId());
        like.setName("我喜欢的音乐");
        like.setSongNum(0);
        like.setS(false);
        like.setPicUrl(Global.HOST_NAME + '/' + Global.DEAFAULT_SHEETPIC);
        sheetMapper.insert(like);
        accountAuth.setLid(like.getId());
        accountAuthMapper.update(accountAuth);
    }

    @Override
    public AccountAuth login(String username, String password) {

        notEmpty(username, "用户名不能为空");
        notEmpty(password, "密码不能为空");
        check(username.length() >= 6 && username.length() <= 16, "用户名长度必须为6-16位");
        check(password.length() >= 6 && password.length() <= 16, "密码长度必须为6-16位");

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
    public void collect(Long uid, Long rid, int t) {
        /*
         * t: 1.专辑， 2.歌单，3.歌手
         * */
        switch (t) {
            case 1: {
                albumCollectMapper.insert(new AlbumCollectKey(uid, rid));
                break;
            }

            case 2: {
                Sheet sheet = sheetMapper.select(rid);
                if (!sheet.getS())
                    throw new CheckException("不能收藏私有歌单");
                sheetCollectMapper.insert(new SheetCollectKey(uid, rid));
                break;
            }

            case 3: {
                artistCollectMapper.insert(new ArtistCollectKey(uid, rid));
                break;
            }

            default:
                throw new CheckException("系统错误");
        }
    }

    @Override
    public void uncollect(Long uid, Long rid, int t) {
        /*
         * t: 1.专辑， 2.歌单，3.歌手
         * */
        switch (t) {
            case 1: {
                albumCollectMapper.delete(new AlbumCollectKey(uid, rid));
                break;
            }

            case 2: {
                sheetCollectMapper.delete(new SheetCollectKey(uid, rid));
                break;
            }

            case 3: {
                artistCollectMapper.delete(new ArtistCollectKey(uid, rid));
                break;
            }

            default:
                throw new CheckException("系统错误");
        }
    }

    @Override
    public Boolean checkCollected(Long uid, Long id, int t) {
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
    public Boolean checkHavedSheet(Long uid, Long sid) {
        return sheetMapper.select(sid).getUid() == uid ? true : false;
    }

    @Override
    public AccountAuth getUserInfo(Long uid) {
        return accountAuthMapper.select(uid);
    }

    @Override
    @Transactional
    public void del(Long uid) {
        accountAuthMapper.delete(uid);
        albumCollectMapper.deleteByUser(uid);
        artistCollectMapper.deleteByUser(uid);
        sheetCollectMapper.deleteByUser(uid);
        sheetService.delByUser(uid);
    }
}
