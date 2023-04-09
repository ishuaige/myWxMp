package com.niuma.mywechatmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyWechatMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWechatMpApplication.class, args);
    }

}
