package com.yaoyili.dao;

import com.yaoyili.model.Sheet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SheetMapper {

    int delete(Long id);

    int insert(Sheet record);

    Sheet select(Long id);

    List<Sheet> selectCollections(Long uid);

    List<Sheet> selectByUid(Long uid);

    List<Sheet> selectPublic(Long uid);

    List<Sheet> selectByName(String name);

    int update(Sheet record);

    int updatePlayTimes(@Param("id") Long id, @Param("times") long times);

    int updateIncr(@Param("id") Long id, @Param("incr") long incr);

    List<Sheet> selectRank(int n);
}