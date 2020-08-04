package com.yaoyili.dao;

import com.yaoyili.model.SheetCollectKey;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SheetCollectMapper {

    SheetCollectKey check(SheetCollectKey key);

    int delete(SheetCollectKey key);

    int deleteBySheet(Long sid);

    int deleteByUser(Long uid);

    int insert(SheetCollectKey record);

}