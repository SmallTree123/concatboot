package com.nylgsc.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nylgsc.Mapper.CacheMapper;
import com.nylgsc.config.IdWorker;
import com.nylgsc.entity.OrderInfo;
import com.nylgsc.kafka.service.Producer;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class CacheServiceImpl extends ServiceImpl<CacheMapper,OrderInfo> implements CacheService {

    private final RedisTemplate redisTemplate;
    private final CacheMapper cacheMapper;
    private final Producer producer;
    private final Redisson redisson;
    private static final AtomicLong ATOMIC_LONG = new AtomicLong(0);
    private static final String lock_name = "order_request_lock";

    @Override
    public List<OrderInfo> getAllOrder() {
        RLock lock = redisson.getLock(lock_name+System.currentTimeMillis());
        List<OrderInfo> allOrderInfo = null;
        try {
            lock.lock();
            if (ObjectUtils.isEmpty(redisTemplate.opsForValue().get("orders"))){
                allOrderInfo = cacheMapper.getAllOrder();
                redisTemplate.opsForValue().set("orders", allOrderInfo,5, TimeUnit.SECONDS);
                System.out.println("请求数据库......");
                return allOrderInfo;
            }else {
                System.out.println("请求redis缓存......");
                return (List<OrderInfo>) redisTemplate.opsForValue().get("orders");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return allOrderInfo;
    }

    @Override
    public void saveOrder() {
//        IdWorker idWorker = new IdWorker();
//        long orderId = idWorker.nextId();
       

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(ATOMIC_LONG.addAndGet(1));
//        orderInfo.setTransNo("transNo"+orderId);
        orderInfo.setConsumName("王乐乐");
        orderInfo.setConsumAddress("北京市海淀区");
        orderInfo.setConsumPhone("15965232548");
        orderInfo.setOrderAmount(new BigDecimal("98.5"));
        orderInfo.setPayStatus("0");
        producer.send(orderInfo);

    }
}
