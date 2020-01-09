package com.yaoyili.dao;

import com.yaoyili.model.Sheet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SheetMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Sheet record);

    Sheet selectByPrimaryKey(Integer id);

    List<Sheet> selectCollections(int uid);

    List<Sheet> selectByUid(Integer uid);

    List<Sheet> selectPublic(Integer uid);

    List<Sheet> selectByName(String name);

    int updateByPrimaryKeySelective(Sheet record);

    int updatePlayTimes(@Param("id") int id, @Param("times") long times);

    int updateIncr(@Param("id") int id, @Param("incr") long incr);

    List<Sheet> selectRank(int n);
}