package com.yaoyili.dao;

import com.yaoyili.model.Sheet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Sheet record);

    Sheet selectByPrimaryKey(Integer id);

    List<Sheet> selectByUid(Integer uid);

    List<Sheet> selectByName(String name);

    int updateByPrimaryKeySelective(Sheet record);
}