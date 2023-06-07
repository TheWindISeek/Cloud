package com.cloud.cloudcomputation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.cloud.cloudcomputation.dao"})
@ComponentScan(basePackages = {"com.*"})
public class CloudComputationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudComputationApplication.class, args);
    }

}
