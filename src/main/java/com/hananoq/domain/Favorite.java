package com.hananoq.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * favorite
 *
 * @author Hananoq
 */
@Data
public class Favorite implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer goodsNumber;

    private static final long serialVersionUID = 1L;
}