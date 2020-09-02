package com.hananoq.service;

import com.hananoq.domain.Orders;

import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/12 20:52
 **/
public interface OrdersService {

    int deleteByPrimaryKey(String number);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> getAllDeliveryOrdersByUsername(String username);

    List<Orders> getAllDeliveryOrders();

    List<Orders> getAllShippingOrders();

    List<Orders> getAllDeliveryOrdersContainPic();

    List<Orders> getAllShippingOrdersContainPic();

    Orders selectByPrimaryKeyContainPic(String number);

    void addOrders(Orders orders);
}
