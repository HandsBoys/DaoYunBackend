package com.dy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SystemApplicationTests {

    @Autowired
    private RedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

}
