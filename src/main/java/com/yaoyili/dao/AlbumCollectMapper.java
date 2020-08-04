package com.yaoyili.dao;

import com.yaoyili.model.AlbumCollectKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumCollectMapper {

    AlbumCollectKey check(AlbumCollectKey key);

    int delete(AlbumCollectKey key);

    int deleteByAlbum(Long aid);

    int deleteByUser(Long uid);

    int insert(AlbumCollectKey record);
}