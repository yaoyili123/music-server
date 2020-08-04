package com.yaoyili.dao;

import com.yaoyili.model.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistMapper {

    int delete(Long id);

    int insert(Artist record);

    int total();

    Artist select(Long id);

    List<Artist> selectCollections(Long uid);

    List<Artist> selectByName(String name);

    List<Artist> selectAll(
            @Param("offset") int offset,
            @Param("size") int pageSize,
            @Param("name")String filterName);

    int update(Artist record);
}