package com.yaoyili.service;

import com.yaoyili.model.AccountAuth;
import com.yaoyili.model.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public void register(String username, String password, String nickname, MultipartFile file);

    public AccountAuth login(String username, String password);

    public void collect(int uid, int rid, int t);

    public void uncollect(int uid, int rid, int t);

    public Boolean checkCollected(int uid, int id, int t);

    public Boolean checkHavedSheet(int uid, int sid);

    public List findCollections(int tid, int uid);
}
