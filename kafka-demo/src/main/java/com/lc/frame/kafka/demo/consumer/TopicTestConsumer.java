package com.lc.frame.kafka.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicTestConsumer {

//    @KafkaListener(topics = "test-topic", groupId = "app-group-1")
//    public void receive(String message){
//        System.out.println("收到一条信息:" + message);
//    }
}
