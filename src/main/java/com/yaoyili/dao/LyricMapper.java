package com.yaoyili.dao;

import com.yaoyili.model.Lyric;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LyricMapper {

    int delete(Long id);

    int insert(Lyric record);

    Lyric select(Long id);

    int update(Lyric record);
}