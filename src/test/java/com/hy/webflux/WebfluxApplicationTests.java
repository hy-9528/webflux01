package com.hy.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebfluxApplicationTests {

    @Test
    void contextLoads() {
        String id = 1+1+"";
        System.out.println(id);
    }

}
