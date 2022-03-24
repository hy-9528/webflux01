package com.hy.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WebfluxApplication.class, args);
        System.out.println("hello git1");
        System.out.println("hot-fix test");
    }

}
