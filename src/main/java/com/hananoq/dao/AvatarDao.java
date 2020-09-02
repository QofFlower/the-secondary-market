package com.hananoq.dao;

import com.hananoq.domain.Avatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AvatarDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Avatar record);

    int insertSelective(Avatar record);

    Avatar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Avatar record);

    int updateByPrimaryKey(Avatar record);

    Avatar findByUserId(@Param("UserId") Integer UserId);

    void updateByUserId(@Param("userId") Integer userId, @Param("avatarName") String avatarName);
}