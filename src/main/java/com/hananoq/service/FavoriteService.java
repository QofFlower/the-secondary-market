package com.hananoq.service;

import com.hananoq.domain.Favorite;
import com.hananoq.domain.Goods;

import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/16 11:09
 **/
public interface FavoriteService {

    int deleteByPrimaryKey(Integer id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    List<Goods> findGoodsByUserId(Integer userId);

    List<Goods> findGoodsByCurrentUser();

    void addLike(Integer number);

    Favorite hasFavorite(Integer number);
}
