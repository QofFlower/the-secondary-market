package com.hananoq.service.implement;

import com.hananoq.dao.SaleDao;
import com.hananoq.domain.Sale;
import com.hananoq.domain.User;
import com.hananoq.service.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author :花のQ
 * @since 2020/8/10 19:21
 **/
@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleDao saleDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return saleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sale record) {
        return saleDao.insert(record);
    }

    @Override
    public int insertSelective(Sale record) {
        return saleDao.insertSelective(record);
    }

    @Override
    public Sale selectByPrimaryKey(Integer id) {
        return saleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Sale record) {
        return saleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Sale record) {
        return saleDao.updateByPrimaryKey(record);
    }

    @Override
    public User findUserByGoodsNumber(Integer number) {
        return saleDao.findUserByGoodsNumber(number);
    }
}
