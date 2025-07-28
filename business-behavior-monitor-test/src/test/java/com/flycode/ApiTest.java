package com.flycode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiTest {


    @Test
    public void test() throws InterruptedException {
        String name = "sss";
        int age = 18;
        log.info("name:{},age:{}", name, age);
        new CountDownLatch(1).await();
    }
}
