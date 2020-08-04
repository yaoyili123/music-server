package com.yaoyili.dao;

import com.yaoyili.model.ArtistCollectKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtistCollectMapper {

    ArtistCollectKey check(ArtistCollectKey key);

    int delete(ArtistCollectKey key);

    int deleteByArtist(Long aid);

    int deleteByUser(Long uid);

    int insert(ArtistCollectKey record);
}