package com.yaoyili.dao;

import com.yaoyili.model.Lyric;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LyricMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lyric record);

    Lyric selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Lyric record);
}