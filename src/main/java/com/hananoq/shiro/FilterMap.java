package com.hananoq.shiro;

import java.util.LinkedHashMap;

/**
 * @author :花のQ
 * @since 2020/7/7 9:43
 * 配置拦截的路径map类
 **/
public class FilterMap {

    private static LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

    static {
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/index", "anon");
        filterMap.put("/user/getImage", "anon");
        filterMap.put("/user/checkname", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/user/forget", "anon");
        filterMap.put("/goods/index", "anon");
        filterMap.put("/goods/allGoods", "anon");
        filterMap.put("/goods/detail/*", "anon");
        filterMap.put("/goods/search", "anon");

        filterMap.put("/user/logout", "logout");

        filterMap.put("/static/**", "anon");
        filterMap.put("*.css", "anon");
        filterMap.put("*.js", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/user/test", "anon");
        filterMap.put("/layuiadmin/**", "anon");

        filterMap.put("/**", "authc");


    }

    public static LinkedHashMap<String, String> getFilterMap() {
        return filterMap;
    }
}
