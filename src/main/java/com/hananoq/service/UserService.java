package com.hananoq.service;

import com.hananoq.domain.User;

/**
 * @author :花のQ
 * @since 2020/7/6 19:14
 **/
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(User user);

    void checkUsername(String username);

    User findByUsername(String username);

    void update(User user);

    void updatePassword(String password);

    void checkPassword(String password);
}
