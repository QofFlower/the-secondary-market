package com.hananoq.service.implement;

import com.hananoq.dao.GoodsDao;
import com.hananoq.dao.OrdersDao;
import com.hananoq.dao.StateDao;
import com.hananoq.domain.Goods;
import com.hananoq.domain.Orders;
import com.hananoq.domain.State;
import com.hananoq.domain.User;
import com.hananoq.domain.global.GlobalVar;
import com.hananoq.service.GoodsService;
import com.hananoq.service.OrdersService;
import com.hananoq.utils.GenerateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/12 20:53
 **/
@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersDao ordersDao;

    @Resource
    private StateDao stateDao;

    @Override
    public int deleteByPrimaryKey(String number) {
        return ordersDao.deleteByPrimaryKey(number);
    }

    @Override
    public int insert(Orders record) {
        return ordersDao.insert(record);
    }

    @Override
    public int insertSelective(Orders record) {
        return ordersDao.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(String number) {
        return ordersDao.selectByPrimaryKey(number);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return ordersDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return ordersDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Orders> getAllDeliveryOrdersByUsername(String username) {
        return ordersDao.getAllDeliveryOrdersByUsername(username);
    }

    @Override
    public List<Orders> getAllDeliveryOrders() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return ordersDao.getAllDeliveryOrdersByUsername(user.getUsername());
    }

    @Override
    public List<Orders> getAllShippingOrders() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return ordersDao.getAllShippingOrdersByUsername(user.getUsername());
    }

    @Override
    public List<Orders> getAllDeliveryOrdersContainPic() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Orders> orders = ordersDao.getAllDeliveryOrdersContainPic(user.getUsername());
        // 给商品添加完整路径
        orders.forEach(o ->
                o.setGoodsPicture(GlobalVar.goodsPicUrl + o.getGoodsPicture())
        );
        return orders;
    }

    @Override
    public List<Orders> getAllShippingOrdersContainPic() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Orders> orders = ordersDao.getAllShippingOrdersContainPic(user.getUsername());
        // 给商品添加完整路径
        orders.forEach(o ->
                o.setGoodsPicture(GlobalVar.goodsPicUrl + o.getGoodsPicture())
        );
        return orders;
    }

    @Override
    public Orders selectByPrimaryKeyContainPic(String number) {
        return ordersDao.selectByPrimaryKeyContainPic(number);
    }

    @Override
    public void addOrders(Orders orders) {
        // 随机订单号
        orders.setNumber(GenerateUtils.randomOrderId());

        // 初始化订单状态
        State state = new State();
        state.setOrderNumber(orders.getNumber());
        state.setStatus("已下单");
        stateDao.insert(state);

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        orders.setDeliveryName(user.getUsername());
        orders.setDeliveryAddress(user.getAddress());
        orders.setCompleteTime(new Date());

        ordersDao.insert(orders);
    }


}
