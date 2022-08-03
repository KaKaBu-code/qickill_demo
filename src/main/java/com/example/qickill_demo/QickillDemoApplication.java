package com.example.qickill_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.qickill_demo.mapper")
public class QickillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QickillDemoApplication.class, args);
    }

}
