package com.nylgsc.consumer;

import com.alibaba.fastjson.JSON;
import com.nylgsc.kafka.ConsumerApplication;
import com.nylgsc.kafka.entity.User;
import com.nylgsc.kafka.service.IdWorker;
import com.nylgsc.kafka.service.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ConsumerApplication.class)
@RunWith(SpringRunner.class)
public class KafkaConsumerApplicationTests {


    @Autowired
    Producer producer;

    @Test
    public void contextLoads() {
        IdWorker idWorker = new IdWorker();
        for (int i = 0; i < 10; i++) {
            try {
                User user = new User(idWorker.nextId()+"","啦啦啦啦","tansno"+idWorker.nextId(),"18338154519","北京市");
                String jsonString = JSON.toJSONString(user);
                producer.send(jsonString);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
