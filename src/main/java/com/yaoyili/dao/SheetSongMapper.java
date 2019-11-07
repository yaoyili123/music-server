package com.yaoyili.dao;

import com.yaoyili.model.SheetSongKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SheetSongMapper {

    SheetSongKey selectByPrimaryKey(SheetSongKey key);

    int deleteByPrimaryKey(SheetSongKey key);

    int insert(SheetSongKey record);

    int insertSelective(SheetSongKey record);
}