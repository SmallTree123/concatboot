package com.nylgsc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nylgsc.entity.OrderInfo;

import java.util.List;

public interface CacheService extends IService<OrderInfo> {

    List<OrderInfo> getAllOrder();

    void saveOrder();

}
