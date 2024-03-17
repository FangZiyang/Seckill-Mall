package com.miaoshaproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class APP {

    @RequestMapping("/")
    public String home() {
        return "hello web";
    }

    public static void main(String[] args) {
        System.out.println("hello web word!");
        SpringApplication.run(APP.class, args);

    }
}
