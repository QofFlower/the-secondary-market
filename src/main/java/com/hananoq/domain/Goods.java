package com.hananoq.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * goods
 *
 * @author Hananoq
 */
@Data
public class Goods implements Serializable {
    private Integer number;

    private String name;

    private String picture;

    private Double price;

    private String description;

    private static final long serialVersionUID = 1L;
}