package com.hananoq.dao;

import com.hananoq.domain.Favorite;
import com.hananoq.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    List<Goods> selectGoodsByUserId(Integer userId);

    Favorite selectByUserIdAndGoodsNumber(@Param("userId") Integer id,
                                       @Param("goodsNumber") Integer number);
}