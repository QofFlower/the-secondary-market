package com.hananoq.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :花のQ
 * @since 2020/7/7 9:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result {
    private int code;//200表示成功，400表示失败
    private String message;
    private Object data;

    public static Result succ(String message) {
        return new Result(200, message, null);
    }

    public static Result success(Object data) {
        return new Result(200, null, data);
    }

    public static Result fail(String message) {
        return new Result(400, message, null);
    }

    public static Result succ(int code, Object data) {
        return new Result(code, "", data);
    }

}
