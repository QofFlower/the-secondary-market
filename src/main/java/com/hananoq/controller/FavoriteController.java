package com.hananoq.controller;

import com.alibaba.fastjson.JSON;
import com.hananoq.domain.Goods;
import com.hananoq.domain.response.Result;
import com.hananoq.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/16 13:00
 **/
@Controller
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 收藏页面
     *
     * @return favorite.html
     */
    @GetMapping("detail")
    public String favorite() {
        return "favorite";
    }

    /**
     * 获取用户收藏列表
     *
     * @return JSON
     */
    @GetMapping("all")
    @ResponseBody
    public String all() {
        List<Goods> all = favoriteService.findGoodsByCurrentUser();
        return JSON.toJSONString(Result.success(all));
    }

    /**
     * 添加啊收藏
     * @param number 商品号
     * @return JSON
     */
    @PostMapping("add")
    @ResponseBody
    public String add(Integer number){
        favoriteService.addLike(number);
        return JSON.toJSONString(Result.succ("添加收藏成功"));
    }

}
