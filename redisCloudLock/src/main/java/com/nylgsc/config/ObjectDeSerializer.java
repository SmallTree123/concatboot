package com.nylgsc.config;

import com.alibaba.fastjson.JSON;
import com.nylgsc.entity.OrderInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ObjectDeSerializer implements Deserializer<OrderInfo> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public OrderInfo deserialize(String s, byte[] bytes) {
        return JSON.parseObject(bytes, OrderInfo.class);
    }

    @Override
    public void close() {

    }
}
