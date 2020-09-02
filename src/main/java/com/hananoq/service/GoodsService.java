package com.hananoq.service;

import com.hananoq.domain.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/8 19:34
 **/
public interface GoodsService {

    int deleteByPrimaryKey(Integer number);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();

    void saveGoods(Goods goods, MultipartFile file) throws IOException;

    List<Goods> fuzzyFind(String param);
}
