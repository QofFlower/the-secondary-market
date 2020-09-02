package com.hananoq;

import com.hananoq.dao.FavoriteDao;
import com.hananoq.domain.Goods;
import com.hananoq.service.FavoriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author :花のQ
 * @since 2020/7/16 11:13
 **/
@SpringBootTest
//@RunWith(SpringRunner.class)
public class FavoriteTest {

    @Autowired
    private FavoriteService favoriteService;

    @Resource
    private FavoriteDao favoriteDao;

    @Test
    public void test1() {
        List<Goods> goodsByUserId = favoriteService.findGoodsByUserId(4);
        System.out.println(goodsByUserId);
    }

    @Test
    public void test2() {
        System.out.println(UUID.randomUUID());
    }

    @Test
    public void find() {
        System.out.println(favoriteDao.selectByUserIdAndGoodsNumber(3, 5));
    }
}
