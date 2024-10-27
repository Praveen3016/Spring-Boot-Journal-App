//package com.spring_start.spring_start.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@SpringBootTest
//@Slf4j
//public class RedisTest {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void redisTest() {
//        // Setting a value in Redis
//        redisTemplate.opsForValue().set("email", "praveenbhau@gmail.com");
//
//        // Retrieving the value from Redis
//        Object email = redisTemplate.opsForValue().get("email");
//        log.info("Redis provided this: {}", email);
//    }
//}
