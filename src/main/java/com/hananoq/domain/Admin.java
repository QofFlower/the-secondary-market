package com.hananoq.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * admin
 * @author 
 */
@Data
public class Admin implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String perms;

    private static final long serialVersionUID = 1L;
}