package com.yaoyili.dao;

import com.yaoyili.model.Lyric;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LyricMapper {
    int deleteByPrimaryKey(Integer id);

    Lyric findLyric(Integer sid);

    int insert(Lyric record);

    int insertSelective(Lyric record);

    Lyric selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Lyric record);

    int updateByPrimaryKeyWithBLOBs(Lyric record);

    int updateByPrimaryKey(Lyric record);
}