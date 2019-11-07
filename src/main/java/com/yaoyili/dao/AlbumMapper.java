package com.yaoyili.dao;

import com.yaoyili.model.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumMapper {

    int total();

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Album record);

    List<Album> selectByName(String name);

    List<Album> selectAll(@Param("offset") int offset,
                          @Param("size") int pageSize,
                          @Param("name")String filterName,
                          @Param("artistId")int aid);

    Album selectByPrimaryKey(Integer id);

    List<Album> selectByAid(Integer id);

    int updateByPrimaryKeySelective(Album record);
}