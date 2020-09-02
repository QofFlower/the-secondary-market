package com.hananoq.service;

import com.hananoq.domain.Sale;
import com.hananoq.domain.User;

/**
 * @author :花のQ
 * @since 2020/8/10 19:21
 **/
public interface SaleService {

    int deleteByPrimaryKey(Integer id);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    User findUserByGoodsNumber(Integer number);
}
