package com.hananoq.service;

import com.hananoq.domain.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author :花のQ
 * @since 2020/7/7 14:09
 **/
public interface AvatarService {

    int deleteByPrimaryKey(Integer id);

    int insert(Avatar record);

    int insertSelective(Avatar record);

    Avatar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Avatar record);

    int updateByPrimaryKey(Avatar record);

    void saveAvatar(Integer userId, MultipartFile avatar) throws IOException;

    Avatar findByUserId(Integer UserId);

    void updateByUserId(Integer UserId, MultipartFile file) throws IOException;

    Avatar findCurrent();
}
