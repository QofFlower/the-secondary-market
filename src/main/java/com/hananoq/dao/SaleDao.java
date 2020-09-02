package com.hananoq.dao;

import com.hananoq.domain.Sale;
import com.hananoq.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SaleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    User findUserByGoodsNumber(@Param("goodsNumber") Integer number);
}