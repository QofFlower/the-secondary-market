package com.hananoq.dao;

import com.hananoq.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsernameAndPassword(User user);

    User findByUsername(@Param("username") String username);

    void update(User user);

    void updatePassword(@Param("userId") Integer userId,@Param("password") String password);
}