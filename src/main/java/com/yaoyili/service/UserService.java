package com.yaoyili.service;

import com.yaoyili.model.AccountAuth;
import com.yaoyili.model.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public void register(String username, String password, String nickname, MultipartFile file);

    public AccountAuth login(String username, String password);

    public void collect(Long uid, Long rid, int t);

    public void uncollect(Long uid, Long rid, int t);

    public Boolean checkCollected(Long uid, Long id, int t);

    public Boolean checkHavedSheet(Long uid, Long sid);

//    public List findCollections(int tid, int uid);

    public AccountAuth getUserInfo(Long uid);

    public void del(Long uid);
}
