package com.hananoq.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * state
 * @author Hananoq
 */
@Data
public class State implements Serializable {
    private Integer id;

    private String orderNumber;

    private String status;

    private static final long serialVersionUID = 1L;
}