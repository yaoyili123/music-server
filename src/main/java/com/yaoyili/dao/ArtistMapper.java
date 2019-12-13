package com.yaoyili.dao;

import com.yaoyili.model.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Artist record);

    int total();

    Artist selectByPrimaryKey(Integer id);

    List<Artist> selectCollections(int uid);

    List<Artist> selectByName(String name);

    List<Artist> selectAll(
            @Param("offset") int offset,
            @Param("size") int pageSize,
            @Param("name")String filterName);

    int updateByPrimaryKeySelective(Artist record);
}