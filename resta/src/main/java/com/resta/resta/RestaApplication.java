package com.resta.resta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.resta.resta.dao")
public class RestaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaApplication.class, args);
    }

}
