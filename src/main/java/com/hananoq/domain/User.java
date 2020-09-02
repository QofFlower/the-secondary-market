package com.hananoq.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * user
 *
 * @author 花のQ
 */
@Data
@SuppressWarnings("all")
public class User implements Serializable {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 20)
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11)
    private String phone;

    private Double money = Double.valueOf(0);

    private String address;

    private String perms;

    private static final long serialVersionUID = 1L;
}