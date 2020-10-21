package com.wusd.familyfinanceapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.wusd.familyfinanceapi.mapper")
@SpringBootApplication(scanBasePackages = {"com.wusd.familyfinanceapi"})
public class FamilyFinanceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyFinanceApiApplication.class, args);
    }
}
