package com.yaoyili.dao;

import com.yaoyili.model.AccountAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountAuthMapper {
    int delete(Long id);

    int insert(AccountAuth record);

    AccountAuth select(Long id);

    AccountAuth checkRepeated(String username);

    AccountAuth checkUserAuth(@Param("username")String username,@Param("password") String password);

    int update(AccountAuth record);
}