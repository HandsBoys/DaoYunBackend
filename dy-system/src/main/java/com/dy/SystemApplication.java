package com.dy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author cxj
 */
@SpringBootApplication
@EnableAsync
@MapperScan("com.dy.mapper")
public class SystemApplication {
    public static void main(String[] args) {
            SpringApplication.run(SystemApplication.class, args);
    }

}
