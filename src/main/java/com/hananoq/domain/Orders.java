package com.hananoq.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * orders
 *
 * @author Hananoq
 */
@Data
public class Orders implements Serializable {
    private String number;

    private Integer goodsNumber;

    private String goodsName;

    private Double price;

    private String shippingName;

    private String deliveryName;

    private String shippingAddress;

    private String deliveryAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date completeTime;

    private String description;

    private String goodsPicture;

    private String status;

    private static final long serialVersionUID = 1L;
}