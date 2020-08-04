package com.yaoyili.dao;

import com.yaoyili.model.SheetSongKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SheetSongMapper {

    SheetSongKey select(SheetSongKey key);

    List<SheetSongKey> selectBySong(Long songid);

    int delete(SheetSongKey key);

//    int deleteBySong(Long songid);

    int deleteBySheet(Long sheetid);

    int insert(SheetSongKey record);
}