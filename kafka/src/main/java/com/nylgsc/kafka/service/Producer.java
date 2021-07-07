package com.nylgsc.kafka.service;

import com.nylgsc.kafka.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@AllArgsConstructor
@Component(value = "producerKafka")
public class Producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object t){
        kafkaTemplate.send("sucKafka",t);
    }

}
