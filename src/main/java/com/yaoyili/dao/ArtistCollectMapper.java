package com.yaoyili.dao;

import com.yaoyili.model.ArtistCollectKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistCollectMapper {

    ArtistCollectKey check(ArtistCollectKey key);

    int deleteByPrimaryKey(ArtistCollectKey key);

    int insert(ArtistCollectKey record);

    int insertSelective(ArtistCollectKey record);
}