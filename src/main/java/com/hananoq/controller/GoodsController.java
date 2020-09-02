package com.hananoq.controller;

import com.alibaba.fastjson.JSON;
import com.hananoq.domain.Favorite;
import com.hananoq.domain.Goods;
import com.hananoq.domain.User;
import com.hananoq.domain.response.Result;
import com.hananoq.service.FavoriteService;
import com.hananoq.service.GoodsService;
import com.hananoq.service.SaleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author :花のQ
 * @since 2020/7/8 19:13
 **/
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 商品首页
     *
     * @return goodslist.html
     */
    @GetMapping({"index", "/"})
    public String index() {
        return "goodslist";
    }

    /**
     * 获取商品列表
     *
     * @return JSON
     */
    @GetMapping("allGoods")
    @ResponseBody
    public String getGoods() {
        List<Goods> all = goodsService.findAll();
        return JSON.toJSONString(Result.success(all));
    }

    /**
     * 搜索商品
     *
     * @param param 参数
     * @return JSON
     */
    @GetMapping("search")
    @ResponseBody
    public String search(String param) {
        List<Goods> goods = goodsService.fuzzyFind("%" + param + "%");
        return JSON.toJSONString(Result.success(goods));
    }

    /**
     * 添加商品页面
     *
     * @return addgoods.html
     */
    @GetMapping("addGoods")
    public String toAddGoods() {
        return "addgoods";
    }

    /**
     * 添加商品
     *
     * @return JSON
     */
    @PostMapping("addGoods")
    @ResponseBody
    public String addGoods(Goods goods, MultipartFile file) throws IOException {

        goodsService.saveGoods(goods, file);
        return JSON.toJSONString(Result.succ("添加商品成功"));
    }

    /**
     * 商品详情
     *
     * @param number 商品号
     * @return goodsdetail.html
     */
    @GetMapping("detail/{number}")
    public String detail(@PathVariable Integer number, Model model) {
        Goods goods = goodsService.selectByPrimaryKey(number);
        //System.out.println("The goods to show for user " + goods);

        User user = saleService.findUserByGoodsNumber(number);
        User login = (User) SecurityUtils.getSubject().getPrincipal();

        if (login != null) {
            Favorite faGoods = favoriteService.hasFavorite(goods.getNumber());
            //System.out.println("The favorite goods: " + faGoods);
            if (faGoods != null) {
                model.addAttribute("hasLike", 1);
            } else {
                model.addAttribute("hasLike", 0);
            }
        }

        model.addAttribute("self", Objects.equals(user, login));// 是否为自己的商品
        model.addAttribute("goods", goods);// 商品
        model.addAttribute("seller", user);// 卖家
        return "goodsdetail";
    }

}
