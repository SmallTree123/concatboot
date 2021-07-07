package com.nylgsc.config;

import com.alibaba.fastjson.JSON;
import com.nylgsc.entity.OrderInfo;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ObjectSerializer implements Serializer<OrderInfo> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, OrderInfo orderInfo) {
        return JSON.toJSONBytes(orderInfo);
    }

    @Override
    public void close() {

    }
}
