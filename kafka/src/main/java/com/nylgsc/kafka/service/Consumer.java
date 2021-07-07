package com.nylgsc.kafka.service;

import com.alibaba.fastjson.JSON;
import com.nylgsc.kafka.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Test;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Optional;


@Slf4j
@Component
public class Consumer {

    @KafkaListener(topics = "sucKafka")
    public void listener(ConsumerRecord record){
        Optional msg = Optional.ofNullable(record.value());
        if (msg.isPresent()){
            log.info("hello-消费者");
            System.out.println(msg.get());
        }
    }
}
