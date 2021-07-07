package com.nylgsc.service;

import com.nylgsc.entity.OrderMaster;
import org.springframework.stereotype.Service;

@Service
public interface OrderMasterService {
    public void insert(OrderMaster orderMaster);
}
