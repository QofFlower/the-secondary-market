package com.hananoq.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * avatar
 * @author 
 */
@Data
public class Avatar implements Serializable {
    private Integer id;

    private Integer userId;

    private String path;

    private static final long serialVersionUID = 1L;
}