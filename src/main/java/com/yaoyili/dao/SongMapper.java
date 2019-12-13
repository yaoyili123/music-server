package com.yaoyili.dao;

import com.yaoyili.controller.resbeans.SongResponse;
import com.yaoyili.model.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SongMapper {

    int total();

    int deleteByPrimaryKey(Integer id);

    List<Song> selectByName(String name);

    List<Song> selectAll(@Param("offset") int offset,
                         @Param("size") int pageSize,
                         @Param("name")String filterName,
                         @Param("albumId")int aid);

    List<SongResponse> selectByArtist(Integer id);

    List<Song> selectByAlbum(Integer id);

    List<Song> selectBySheet(Integer id);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song record);
}