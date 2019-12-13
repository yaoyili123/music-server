package com.yaoyili.dao;

import com.yaoyili.model.SheetCollectKey;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SheetCollectMapper {

    SheetCollectKey check(SheetCollectKey key);

    int deleteByPrimaryKey(SheetCollectKey key);

    int insert(SheetCollectKey record);

    int insertSelective(SheetCollectKey record);
}