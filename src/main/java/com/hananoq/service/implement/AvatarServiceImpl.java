package com.hananoq.service.implement;

import com.hananoq.dao.AvatarDao;
import com.hananoq.domain.Avatar;
import com.hananoq.domain.User;
import com.hananoq.domain.global.GlobalVar;
import com.hananoq.service.AvatarService;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author :花のQ
 * @since 2020/7/7 14:10
 **/
@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Resource
    private AvatarDao avatarDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return avatarDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Avatar record) {
        return avatarDao.insert(record);
    }

    @Override
    public int insertSelective(Avatar record) {
        return avatarDao.insertSelective(record);
    }

    @Override
    public Avatar selectByPrimaryKey(Integer id) {
        return avatarDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Avatar record) {
        return avatarDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Avatar record) {
        return avatarDao.updateByPrimaryKey(record);
    }

    @Override
    public void saveAvatar(Integer userId, MultipartFile avatar) throws IOException {
        if (avatar == null) {

            //用户上传的头像为空，使用默认头像
            Avatar saveAvatar = new Avatar();
            saveAvatar.setUserId(userId);
            saveAvatar.setPath(GlobalVar.defaultAvatar);
            avatarDao.insert(saveAvatar);

        } else {
            //生成图片名称
            String avatarName = userId + "&" + UUID.randomUUID().toString() + ".jpg";

            //文件存储
            //获取相对路径
            File file = new File(GlobalVar.avatarPath + avatarName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            file.setWritable(true, false);
            avatar.transferTo(file);

            //向数据库头像表中插入数据
            Avatar saveAvatar = new Avatar();
            saveAvatar.setUserId(userId);
            saveAvatar.setPath(avatarName);
            avatarDao.insert(saveAvatar);
        }

    }

    @Override
    public Avatar findByUserId(Integer UserId) {
        return avatarDao.findByUserId(UserId);
    }

    @Override
    public void updateByUserId(Integer UserId, MultipartFile file) throws IOException {
        Avatar avatar = avatarDao.findByUserId(UserId);
        File file1 = null;
        if (GlobalVar.defaultAvatar.equals(avatar.getPath())) {
            // 老头像为默认头像

            //生成图片名称
            String avatarName = UserId + "&" + UUID.randomUUID().toString() + ".jpg";
            avatarDao.updateByUserId(UserId, avatarName);
            file1 = new File(GlobalVar.avatarPath + avatarName);
        } else {
            //覆盖原来的头像文件
            file1 = new File(GlobalVar.avatarPath + avatar.getPath());
        }
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        if (!file1.exists()) {
            file1.createNewFile();
        }
        file1.setWritable(true, false);
        file.transferTo(file1);
    }

    @Override
    public Avatar findCurrent() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return avatarDao.findByUserId(user.getId());
    }
}
