package com.yaoyili.dao;

import com.yaoyili.model.AlbumCollectKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumCollectMapper {

    AlbumCollectKey check(AlbumCollectKey key);

    int deleteByPrimaryKey(AlbumCollectKey key);

    int insert(AlbumCollectKey record);

    int insertSelective(AlbumCollectKey record);
}