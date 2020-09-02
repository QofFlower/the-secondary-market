package com.hananoq.service.implement;

import com.hananoq.dao.GoodsDao;
import com.hananoq.dao.SaleDao;
import com.hananoq.domain.Goods;
import com.hananoq.domain.Sale;
import com.hananoq.domain.User;
import com.hananoq.domain.global.GlobalVar;
import com.hananoq.service.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author :花のQ
 * @since 2020/7/8 19:34
 **/
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private SaleDao saleDao;

    @Override
    public int deleteByPrimaryKey(Integer number) {
        return goodsDao.deleteByPrimaryKey(number);
    }

    @Override
    public int insert(Goods record) {
        return goodsDao.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsDao.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer number) {
        return goodsDao.selectByPrimaryKey(number);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public void saveGoods(Goods goods, MultipartFile file) throws IOException {
        // 保存商品图片
        String pictureName = UUID.randomUUID() + ".jpg";
        System.out.println("pictureName = " + pictureName);

        //获取路径
        File file1 = new File(GlobalVar.goodsPath + pictureName);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        if (!file1.exists()) {
            file1.createNewFile();
        }
        file1.setWritable(true, false);
        file.transferTo(file1);

        goods.setPicture(pictureName);
        goodsDao.insert(goods);

        // 插入贩卖表
        Sale sale = new Sale();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        sale.setUserid(user.getId());
        sale.setGoodsnumber(goods.getNumber());

        saleDao.insert(sale);

    }

    @Override
    public List<Goods> fuzzyFind(String param) {
        return goodsDao.fuzzyFind(param);
    }
}
