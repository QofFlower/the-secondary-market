package com.hananoq.controller;

import com.alibaba.fastjson.JSON;
import com.hananoq.domain.Orders;
import com.hananoq.domain.State;
import com.hananoq.domain.User;
import com.hananoq.domain.response.Result;
import com.hananoq.service.OrdersService;
import com.hananoq.service.StateService;
import com.hananoq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/12 20:56
 * 订单controller
 **/
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private StateService stateService;

    /**
     * 个人信息也买你的订单预览
     *
     * @param kind delivery order or shipping order
     * @return de view or sh view
     */
    @GetMapping("all/{kind}")
    public String order(@PathVariable String kind) {
        if (kind.equals("delivery"))
            return "deliveryorder";
        else
            return "shippingorder";
    }

    /**
     * 订单详情页面
     *
     * @return orderslist.html
     */
    @GetMapping("list")
    public String orderList() {
        return "orderslist";
    }

    /**
     * 查询所有收货订单，不包含商品图片
     *
     * @return JSON
     */
    @GetMapping("delivery")
    @ResponseBody
    public String delivery() {
        List<Orders> all = ordersService.getAllDeliveryOrders();
        //转换时间类型，不然会显示时间戳
        return JSON.toJSONStringWithDateFormat(Result.succ(0, all), "yyyy-MM-dd HH:mm");
    }

    /**
     * 查询所有收货订单, 包含商品图片
     *
     * @return JSON
     */
    @GetMapping("deliveryPic")
    @ResponseBody
    public String deliveryContainPic() {
        List<Orders> all = ordersService.getAllDeliveryOrdersContainPic();
        System.out.println("The orders list :" + all);
        //转换时间类型，不然会显示时间戳
        return JSON.toJSONStringWithDateFormat(Result.succ(200, all), "yyyy-MM-dd HH:mm");
    }

    /**
     * 查询所有发货订单，不包含商品图片
     *
     * @return JSON
     */
    @GetMapping("shipping")
    @ResponseBody
    public String shipping() {
        List<Orders> all = ordersService.getAllShippingOrders();
        return JSON.toJSONStringWithDateFormat(Result.succ(0, all), "yyyy-MM-dd HH:mm");
    }

    /**
     * 查询所有发货订单，包含商品图片
     *
     * @return JSON
     */
    @GetMapping("shippingPic")
    @ResponseBody
    public String shippingContainPic() {
        List<Orders> all = ordersService.getAllShippingOrdersContainPic();
        return JSON.toJSONStringWithDateFormat(Result.succ(200, all), "yyyy-MM-dd HH:mm");
    }

    /**
     * 订单详情页面
     *
     * @param number order number
     * @return orderdetail.html
     */
    @GetMapping("orderDetail/{kind}")
    public String shippingOrderDetail(@PathVariable Integer kind, String number,
                                      String picture, Model model) {

        Orders order = ordersService.selectByPrimaryKey(number);
        State state = stateService.findByGoodsNumber(order.getNumber());
        model.addAttribute("order", order);
        model.addAttribute("orderStatus", state.getStatus());
        model.addAttribute("picture", picture);

        System.out.println("order = " + order);
        System.out.println("picture = " + picture);

        if (kind == 1) {
            User sell = userService.findByUsername(order.getShippingName());
            model.addAttribute("sell", sell);
            return "deorderdetail";
        } else {
            User buy = userService.findByUsername(order.getDeliveryName());
            model.addAttribute("buy", buy);
            return "shorderdetail";
        }
    }

    /**
     * 修改订单状态
     *
     * @return JSON
     */
    @PatchMapping("updateStatus")
    @ResponseBody
    public String updateStatus(String number, String status) {

        System.out.println("number = " + number);
        System.out.println("status = " + status);

        stateService.updateStatusByOrderNumber(number, status);
        return JSON.toJSONString(Result.succ("更新订单成功"));
    }


    /**
     * 下单页面
     *
     * @return addorder.html
     */
    @GetMapping("addOrder")
    public String toAddOrder() {
        return "addorder";
    }

    /**
     * 添加订单
     *
     * @param orders 订单
     * @return JSON
     */
    @PostMapping("addOrder")
    @ResponseBody
    public String addOrder(Orders orders) {
        //System.out.println("orders = " + orders);
        ordersService.addOrders(orders);
        return JSON.toJSONString(Result.succ("下单成功"));
    }


}
