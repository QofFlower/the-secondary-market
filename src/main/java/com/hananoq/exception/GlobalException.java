package com.hananoq.exception;

import com.hananoq.domain.response.Result;
import com.hananoq.exception.customexception.UserHasExistedException;
import com.hananoq.exception.customexception.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author :花のQ
 * @since 2020/7/7 9:32
 **/
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UnknownAccountException.class)
    public Result exceptionHandler(UnknownAccountException e) {
        log.error("账户认证异常");
        return Result.fail("用户名或密码错误!");
    }

    @ExceptionHandler(RuntimeException.class)
    public Result exceptionHandler(RuntimeException e) {
        log.error("运行时异常");
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result exceptionHandler(IllegalArgumentException e) {
        log.error("校验异常");
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public Result exceptionHandler(IOException e) {
        log.error("文件上传异常");
        return Result.fail(e.getMessage());
    }


    @ExceptionHandler(UserHasExistedException.class)
    public Result exceptionHandler(UserHasExistedException e) {
        log.error("用户名注册异常");
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result exceptionHandler(ArithmeticException e) {
        log.error("分母为0");
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result exceptionHandler(AuthenticationException e) {
        log.error("用户未登录");
        return Result.fail("");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result exceptionHandler(IncorrectCredentialsException e) {
        log.error("密码错误");
        return Result.fail("用户名或密码错误!!");
    }

    @ExceptionHandler(WrongPasswordException.class)
    public Result exceptionHandler(WrongPasswordException e) {
        log.error("密码校验错误");
        return Result.fail(e.getMessage());
    }

}
