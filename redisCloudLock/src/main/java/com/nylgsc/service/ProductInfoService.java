package com.nylgsc.service;

import com.nylgsc.Mapper.ProductInfoMapper;
import com.nylgsc.entity.ProductInfo;
import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@AllArgsConstructor
public class ProductInfoService {

    private final RedissonClient redissonClient;
    private final ProductInfoMapper productInfoMapper;

    public boolean decrementProductStore(Long productId, Integer productQuantity){
        String key = "dec_store_lock_" + productId;
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            lock.lock();
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
            //如果库存为空
            if (productInfo.getProductStock() == 0) {
                return false;
            }
            //简单减库存操作 没有重新写其他接口了
            productInfo.setProductStock(productInfo.getProductStock() - productQuantity);
            productInfoMapper.updateByPrimaryKey(productInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
        return true;

    }
}
