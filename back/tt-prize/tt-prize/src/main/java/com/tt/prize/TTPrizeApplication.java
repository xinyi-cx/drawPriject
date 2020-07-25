package com.tt.prize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.tt.prize.mapper"})
@SpringBootApplication
public class TTPrizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TTPrizeApplication.class, args);
    }

}
