package com.hananoq.controller;


import com.alibaba.fastjson.JSON;
import com.hananoq.domain.Avatar;
import com.hananoq.domain.Goods;
import com.hananoq.domain.User;
import com.hananoq.domain.global.GlobalVar;
import com.hananoq.domain.response.Result;
import com.hananoq.service.AvatarService;
import com.hananoq.service.FavoriteService;
import com.hananoq.service.UserService;
import com.hananoq.utils.CheckCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @author :花のQ
 * @since 2020/7/6 18:06
 **/
@Controller
@RequestMapping("user")
@SuppressWarnings("all")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AvatarService avatarService;
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 首页
     *
     * @return index.html
     */
    @GetMapping({"index", "/"})
    public String index() {
        return "index";
    }

    /**
     * 登录
     *
     * @return login.html
     */
    @GetMapping("login")
    public String login() {
        return "login2";
    }

    /**
     * 获取验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @GetMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        String code = CheckCode.getCheckCode();
        session.setAttribute("code", code);
        BufferedImage image = CheckCode.createImage(code);
        //通过响应方式输出图片
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }

    /**
     * 登录校验
     *
     * @param
     * @return JSON
     */
    @PostMapping("login")
    @ResponseBody
    public String login(String username, String password, String code, HttpSession session) {
        String saveCode = (String) session.getAttribute("code");
        if (!code.equalsIgnoreCase(saveCode)) {
            throw new RuntimeException("验证码不正确");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User login = userService.login(user);
        Avatar avatar = avatarService.findByUserId(login.getId());
        session.setAttribute("user", login);
        session.setAttribute("avatar", GlobalVar.avatarPreURL + avatar.getPath());

        return JSON.toJSONString(Result.succ("登录成功!"));
    }

    /**
     * 前往注册页面
     *
     * @return register.html
     */
    @GetMapping("register")
    public String goRegister() {
        return "register";
    }

    /**
     * 用户提交注册
     *
     * @param user
     * @return redirect:/index
     */
    @PostMapping("register")
    public String register(@Validated User user, MultipartFile avatar, Model model) throws IOException {
        //保存用户
        userService.insert(user);
        //获取用户id
        Integer userId = userService.login(user).getId();
        //存储用户头像信息

        avatarService.saveAvatar(userId, avatar);
        model.addAttribute("register_message", "注册成功，你现在可以进行登录");
        return "login2";
    }

    /**
     * 检查用户名是否已经存在
     *
     * @return json
     */
    @PostMapping("checkname")
    @ResponseBody
    public String checkUsername(String username) {

        userService.checkUsername(username);
        return JSON.toJSONString(Result.success("用户名唯一"));
    }

    /**
     * 跳转用户信息页面
     *
     * @return userinfo.html
     */
    @GetMapping("info")
    public String info() {
        return "userinfo";
    }

    /**
     * 请求当前用户信息
     *
     * @return
     */
    @GetMapping("getInfo")
    @ResponseBody
    public String getInfo() {
        HashMap<String, Object> map = new HashMap<>();
        Avatar current = avatarService.findCurrent();
        map.put("avatarURL", GlobalVar.avatarPreURL + current.getPath());
        map.put("user", SecurityUtils.getSubject().getPrincipal());
        return JSON.toJSONString(Result.success(map));
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return ""
     */
    @PatchMapping("update")
    @ResponseBody
    public String update(@RequestBody User user) {
        userService.update(user);
        return JSON.toJSONString(Result.succ("修改信息成功!"));
    }

    /**
     * 上传新头像
     *
     * @param file
     * @param session
     * @return JSON
     * @throws IOException
     */
    @PostMapping("uploadAvatar")
    @ResponseBody
    public String update(MultipartFile file, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("user");
        avatarService.updateByUserId(user.getId(), file);
        return JSON.toJSONString(Result.succ("头像上传成功"));
    }

    /**
     * 前往修改密码页面
     *
     * @return userpass.html
     */
    @GetMapping("updatePass")
    public String updatePassword() {
        return "userpass";
    }

    /**
     * 验证密码是否输入正确
     *
     * @param password
     * @return JSON
     */
    @PostMapping("checkPass")
    @ResponseBody
    public String checkPassword(String password) {
        userService.checkPassword(password);
        return JSON.toJSONString(Result.succ("密码正确"));
    }

    /**
     * 修改用户密码
     *
     * @param password
     * @return JSON
     */
    @PatchMapping("updatePass")
    @ResponseBody
    public String updatePassword(String password) {

        userService.updatePassword(password);
        return JSON.toJSONString(Result.succ("修改密码成功，请重新登录。"));
    }

    /**
     * 忘记密码页面
     *
     * @return forgetpass.html
     */
    @GetMapping("forget")
    public String forgetPass() {
        return "forgetpass";
    }

    /**
     * 个人主页
     *
     * @return
     */
    @GetMapping("personal")
    public String personal() {
        return "personalpage";
    }

    /**
     * 用户登出
     *
     * @return redirect:/user/login
     */
    @GetMapping("logout")
    public String logout() {
        return "redirect:/user/login";
    }

    /**
     * 测试专用页面
     *
     * @return test.html
     */
    @GetMapping("test")
    public String test() {
        return "test";
    }

    /**
     * 测试axios异步请求
     *
     * @param file
     * @param user
     * @return JSON
     */
    @PostMapping("axios")
    @ResponseBody
    public String axios(MultipartFile file, User user) {
        System.out.println(file.getOriginalFilename());
        System.out.println("user = " + user);
        return "牛逼";
    }
}
