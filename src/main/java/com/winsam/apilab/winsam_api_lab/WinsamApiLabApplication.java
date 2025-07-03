package com.winsam.apilab.winsam_api_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WinsamApiLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinsamApiLabApplication.class, args);
    }

}
