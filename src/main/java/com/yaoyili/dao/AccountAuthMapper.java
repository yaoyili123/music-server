package com.yaoyili.dao;

import com.yaoyili.model.AccountAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountAuth record);

    int insertSelective(AccountAuth record);

    AccountAuth selectByPrimaryKey(Integer id);

    AccountAuth checkRepeated(String username);

    AccountAuth checkUserAuth(@Param("username")String username,@Param("password") String password);

    int updateByPrimaryKeySelective(AccountAuth record);

    int updateByPrimaryKey(AccountAuth record);
}