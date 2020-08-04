package com.yaoyili.dao;

import com.yaoyili.model.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SongMapper {

    int total();

    int delete(Long id);

    List<Song> selectByName(String name);

    List<Song> selectAll(@Param("offset") int offset,
                         @Param("size") int pageSize,
                         @Param("name")String filterName,
                         @Param("albumId")Long aid);

    List<Song> selectByArtist(Long id);

    List<Song> selectByAlbum(Long id);

    List<Song> selectBySheet(Long id);

    int insert(Song record);

    Song select(Long id);

    int update(Song record);
}