package com.dy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cxj
 */
@SpringBootApplication
@MapperScan("com.dy.mapper")
public class SystemApplication {
    public static void main(String[] args) {
            SpringApplication.run(SystemApplication.class, args);
    }

}
