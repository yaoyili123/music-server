package com.yaoyili.dao;

import com.yaoyili.model.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumMapper {

    int total();

    int delete(Long id);

    int deleteByArtist(Long aid);

    int insert(Album record);

    List<Album> selectByName(String name);

    List<Album> selectCollections(Long uid);

    List<Album> selectAll(@Param("offset") int offset,
                          @Param("size") int pageSize,
                          @Param("name")String filterName,
                          @Param("artistId")long aid);

    Album select(Long id);

    List<Album> selectByAid(Long id);

    int update(Album record);

    int updatePlayTimes(@Param("id") int id, @Param("times") long times);

    int updateIncr(@Param("id") int id, @Param("incr") long incr);

    List<Album> selectRank(int n);

}