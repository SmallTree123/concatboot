package com.nylgsc.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class OrderMaster {
    private Long orderId;
    private Integer orderStatus;
    private Integer payStatus;
    private String buyerName;
    private String buyerAddress;
    private String buyerPhone;
    private BigDecimal orderAmount;
    private Long createTime;

}
