package com.hananoq.dao;

import com.hananoq.domain.Category;

public interface CategoryDao {
    int deleteByPrimaryKey(Integer number);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}