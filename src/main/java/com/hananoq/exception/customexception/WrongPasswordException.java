package com.hananoq.exception.customexception;

/**
 * @author :花のQ
 * @since 2020/7/11 16:16
 **/
public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
