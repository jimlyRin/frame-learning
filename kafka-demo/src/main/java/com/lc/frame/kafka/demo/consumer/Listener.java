package com.lc.frame.kafka.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class Listener {

    @KafkaListener(topics = {"#{'${kafka.consumer.topic.test}'}"},groupId = "#{'${kafka.consumer.group.group1}'}")
    public void listenTestSinkGroup1(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        Optional.ofNullable(record.value()).ifPresent(kafkaMessage -> {
            log.info("kafka group1 接收到信息： {}", kafkaMessage);
        });
        ack.acknowledge();
    }

    @KafkaListener(topics = {"#{'${kafka.consumer.topic.test}'}"},groupId = "#{'${kafka.consumer.group.group2}'}")
    public void listenTestSinkGroup2(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        Optional.ofNullable(record.value()).ifPresent(kafkaMessage -> {
            log.info("kafka group2 接收到信息： {}", kafkaMessage);
        });
        ack.acknowledge();
    }
}
