package com.hananoq.service.implement;

import com.hananoq.dao.FavoriteDao;
import com.hananoq.domain.Favorite;
import com.hananoq.domain.Goods;
import com.hananoq.domain.User;
import com.hananoq.service.FavoriteService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :花のQ
 * @since 2020/7/16 11:09
 **/
@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteDao favoriteDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return favoriteDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Favorite record) {
        return favoriteDao.insert(record);
    }

    @Override
    public int insertSelective(Favorite record) {
        return favoriteDao.insertSelective(record);
    }

    @Override
    public Favorite selectByPrimaryKey(Integer id) {
        return favoriteDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Favorite record) {
        return favoriteDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Favorite record) {
        return favoriteDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> findGoodsByUserId(Integer userId) {
        return favoriteDao.selectGoodsByUserId(userId);
    }

    @Override
    public List<Goods> findGoodsByCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return favoriteDao.selectGoodsByUserId(user.getId());
    }

    @Override
    public void addLike(Integer number) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Favorite favorite = new Favorite();
        favorite.setGoodsNumber(number);
        favorite.setUserId(user.getId());
        favoriteDao.insert(favorite);
    }

    @Override
    public Favorite hasFavorite(Integer number) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return favoriteDao.selectByUserIdAndGoodsNumber(user.getId(), number);
    }
}
