package com.hananoq.service.implement;

import com.hananoq.dao.UserDao;
import com.hananoq.domain.User;
import com.hananoq.exception.customexception.UserHasExistedException;
import com.hananoq.exception.customexception.WrongPasswordException;
import com.hananoq.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author :花のQ
 * @since 2020/7/6 19:15
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public User login(User user) {
        /*User foundUser = userDao.findByUsernameAndPassword(user);
        if (foundUser == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }*/
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return (User) subject.getPrincipal();
    }

    @Override
    public void checkUsername(String username) {
        User user = userDao.findByUsername(username);
        //System.out.println("user = " + user);
        System.out.println(user == null);
        if (user != null) {
            throw new UserHasExistedException("该用户名已被注册");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void update(User user) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();
        user.setId(login.getId());
        userDao.update(user);
    }

    @Override
    public void updatePassword(String password) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();
        userDao.updatePassword(login.getId(),password);
    }

    @Override
    public void checkPassword(String password) {
        User login = (User) SecurityUtils.getSubject().getPrincipal();
        if (!password.equals(login.getPassword())){
            throw new WrongPasswordException("当前密码输入不正确");
        }
    }
}
