package com.tt.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan({"com.tt.manage.mapper"})
@SpringBootApplication
@EnableSwagger2
public class TTManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TTManageApplication.class, args);
    }

}
