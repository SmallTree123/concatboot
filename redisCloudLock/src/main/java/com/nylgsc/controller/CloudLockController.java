package com.nylgsc.controller;
import com.nylgsc.config.IdWorker;
import com.nylgsc.entity.OrderMaster;
import com.nylgsc.service.OrderMasterService;
import com.nylgsc.service.ProductInfoService;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.RedissonFairLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Component
@RestController
@RequestMapping(value = "/cloudlock")
public class CloudLockController {

//    private final ProductInfoService productInfoService;
//    private final OrderMasterService orderMasterService;
//    private final RedisTemplate redisTemplate;
//    private final IdWorker idWorker;
//    @PostMapping(value = "/create")
//    public String createOrder(){
//
//        if (!productInfoService.decrementProductStore(1L, 1)) {
//            return "库存不足";
//        }
//        //分布式雪花id
//        long nextId = idWorker.nextId();
//
//        OrderMaster orderMaster = new OrderMaster();
//        //未支付
//        orderMaster.setOrderStatus(0);
//        //未支付
//        orderMaster.setPayStatus(0);
//        orderMaster.setBuyerName("宿崇");
//        orderMaster.setBuyerAddress("北京");
//        orderMaster.setBuyerPhone("18692794847");
//        orderMaster.setOrderAmount(BigDecimal.ZERO);
//        orderMaster.setCreateTime(System.currentTimeMillis());
//        orderMaster.setOrderId(nextId);
//        orderMasterService.insert(orderMaster);
//        return "创建订单成功";
//
//    }
}
