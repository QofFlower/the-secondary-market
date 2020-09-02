package com.hananoq.dao;

import com.hananoq.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersDao {
    int deleteByPrimaryKey(String number);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> getAllDeliveryOrdersByUsername(@Param("username") String username);

    List<Orders> getAllDeliveryOrdersContainPic(@Param("username") String username);

    List<Orders> getAllShippingOrdersByUsername(@Param("username") String username);

    List<Orders> getAllShippingOrdersContainPic(@Param("username") String username);

    Orders selectByPrimaryKeyContainPic(String number);

    //Orders selectByPrimaryKeyContainPic(@Param("number") String number);

}