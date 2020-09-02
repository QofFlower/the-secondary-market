package com.hananoq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hananoq.dao.OrdersDao;
import com.hananoq.dao.UserDao;
import com.hananoq.domain.Orders;
import com.hananoq.domain.User;
import com.hananoq.domain.response.Result;
import com.hananoq.exception.customexception.UserHasExistedException;
import com.hananoq.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class TheSecondaryMarketApplicationTests {

    @Resource
    private UserDao userDao;

    @Resource
    private OrdersDao ordersDao;

    @Autowired
    private GoodsService goodsService;

    @Test
    void contextLoads() {
        User umi = userDao.findByUsername("umi");
        System.out.println(umi);
    }

    @Test
    void test1() {
        String s = JSON.toJSONString(Result.succ("牛逼"));
        System.out.println(s);
    }

    @Test
    void test2() {
        System.out.println(goodsService.findAll());
    }

    @Test
    void test3() {
        List<Orders> orders = ordersDao.getAllDeliveryOrdersByUsername("水无月");
        String s1 = JSON.toJSONString(Result.succ(0, orders));
        String s = JSON.toJSONStringWithDateFormat(Result.succ(0, orders), "yyyy-MM-dd HH:mm");
        System.out.println(s1);
        System.out.println("===============================");
        System.out.println(s);
    }

    @Test
    void test4() {
        System.out.println(System.getProperty("user.dir"));
    }
}
