package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描mapper包
@MapperScan("com.example.mapper")
public class Springbootdemo07Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo07Application.class, args);
    }

}
