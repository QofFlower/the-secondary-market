package com.hananoq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hananoq.dao")
public class TheSecondaryMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheSecondaryMarketApplication.class, args);
    }

}
