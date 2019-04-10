package com.arcsoft.facego;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class ApiApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
