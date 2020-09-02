package com.hananoq.dao;

import com.hananoq.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    int deleteByPrimaryKey(Integer number);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();

    List<Goods> fuzzyFind(@Param("name") String param);
}