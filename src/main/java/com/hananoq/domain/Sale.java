package com.hananoq.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * sale
 * @author 
 */
@Data
public class Sale implements Serializable {
    private Integer id;

    private Integer userid;

    private Integer goodsnumber;

    private static final long serialVersionUID = 1L;
}