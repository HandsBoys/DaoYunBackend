package com.dy.controller;

//import com.dy.system.domain.SysUser;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
