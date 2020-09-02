package com.hananoq.service;

import com.hananoq.domain.State;

/**
 * @author :花のQ
 * @since 2020/8/8 20:36
 **/
public interface StateService {

    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);

    State findByGoodsNumber(String goodsNumber);

    void updateStatusByOrderNumber(String orderNumber, String status);
}
