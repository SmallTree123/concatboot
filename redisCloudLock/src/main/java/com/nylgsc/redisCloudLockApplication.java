package com.nylgsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan(value = "com.nylgsc.Mapper")
@EnableRedisHttpSession
public class redisCloudLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(redisCloudLockApplication.class,args);
    }
}
