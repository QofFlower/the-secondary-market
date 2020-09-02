package com.hananoq.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * category
 * @author 
 */
@Data
public class Category implements Serializable {
    private Integer number;

    private Integer goodsNumber;

    private String name;

    private static final long serialVersionUID = 1L;
}