package com.lc.frame.kafka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApplication  // implements CommandLineRunner
{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(KafkaDemoApplication.class, args);
        // new CountDownLatch(1).await();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Thread.currentThread().join();
//    }
}
