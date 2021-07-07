package com.nylgsc.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
@Data
public class OrderInfo implements Serializable {
    private Long id;
    private String transNo;
    private BigDecimal orderAmount;
    private String consumName;
    private String consumAddress;
    private String consumPhone;
    private String payStatus;
}
