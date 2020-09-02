package com.hananoq.service.implement;

import com.hananoq.dao.StateDao;
import com.hananoq.domain.State;
import com.hananoq.service.StateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author :花のQ
 * @since 2020/8/8 20:37
 **/
@Service
@Transactional
public class StateServiceImpl implements StateService {

    @Resource
    private StateDao stateDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return stateDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(State record) {
        return stateDao.insert(record);
    }

    @Override
    public int insertSelective(State record) {
        return stateDao.insertSelective(record);
    }

    @Override
    public State selectByPrimaryKey(Integer id) {
        return stateDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(State record) {
        return stateDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(State record) {
        return stateDao.updateByPrimaryKey(record);
    }

    @Override
    public State findByGoodsNumber(String goodsNumber) {
        return stateDao.findByGoodsNumber(goodsNumber);
    }

    @Override
    public void updateStatusByOrderNumber(String orderNumber, String status) {
        stateDao.updateStatusByOrderNumber(orderNumber, status);
    }
}
