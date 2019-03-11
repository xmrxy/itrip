package com.wu.lovetrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.wu.dao")
@EnableTransactionManagement
@ComponentScan("com.wu")
public class LovetripApplication {

    public static void main(String[] args) {
        SpringApplication.run(LovetripApplication.class, args);
    }

}

