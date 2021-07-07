package com.nylgsc.mq;

import com.alibaba.fastjson.JSON;
import com.nylgsc.Mapper.CacheMapper;
import com.nylgsc.entity.OrderInfo;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component(value = "KafkaConsumer")
@AllArgsConstructor
public class KafkaConsumer {

    private final CacheMapper cacheMapper;
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    @KafkaListener(topics = "sucKafka")
    public void listener(ConsumerRecord record){
        Optional msg = Optional.ofNullable(record.value());
        if (msg.isPresent()){
            OrderInfo orderInfo = (OrderInfo) msg.get();
            System.out.println("第："+atomicInteger.addAndGet(1)+"次");
            cacheMapper.insert(orderInfo);
        }
    }
}
