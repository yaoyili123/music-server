package com.yaoyili.service.impl;

import com.yaoyili.controller.CheckException;
import com.yaoyili.dao.AccountAuthMapper;
import com.yaoyili.dao.SheetMapper;
import com.yaoyili.model.AccountAuth;
import com.yaoyili.model.Sheet;
import com.yaoyili.service.MainService;
import com.yaoyili.service.UserService;
import com.yaoyili.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountAuthMapper accountAuthMapper;

    @Autowired
    private MainService mainService;

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
        accountAuthMapper.insertSelective(new AccountAuth(username, password, nickname, filename));
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
}
