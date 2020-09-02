package com.hananoq.dao;

import com.hananoq.domain.State;
import org.apache.ibatis.annotations.Param;

public interface StateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);

    State findByGoodsNumber(@Param("goodsNumber") String goodsNumber);

    void updateStatusByOrderNumber(@Param("orderNumber") String orderNumber,
                                   @Param("status") String status);
}