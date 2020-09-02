package com.hananoq;

import com.hananoq.dao.OrdersDao;
import com.hananoq.domain.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :花のQ
 * @since 2020/8/5 18:14
 **/
@SpringBootTest
//@RunWith(SpringRunner.class)
public class OrdersTest {

    @Resource
    private OrdersDao ordersDao;

    @Test
    public void test1() {
        List<Orders> hananoq = ordersDao.getAllDeliveryOrdersContainPic("hananoq");
        hananoq.forEach(System.out::println);
    }
}
