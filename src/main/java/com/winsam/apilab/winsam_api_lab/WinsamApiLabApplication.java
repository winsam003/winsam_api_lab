package com.winsam.apilab.winsam_api_lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.winsam.apilab.winsam_api_lab.comm.mapper")
public class WinsamApiLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinsamApiLabApplication.class, args);
    }

}
