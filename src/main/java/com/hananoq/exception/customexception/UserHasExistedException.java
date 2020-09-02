package com.hananoq.exception.customexception;

/**
 * @author :花のQ
 * @since 2020/7/7 16:16
 * 用户名已存在异常类
 **/
public class UserHasExistedException extends RuntimeException {

    public UserHasExistedException() {
    }

    public UserHasExistedException(String message) {
        super(message);
    }
}
