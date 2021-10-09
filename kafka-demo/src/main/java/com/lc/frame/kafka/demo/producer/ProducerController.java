package com.lc.frame.kafka.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljl
 * @version v1.0
 * @date 2021/01/27
 */
@Slf4j
@RestController
@RequestMapping("/v1/kafka/")
public class ProducerController {

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.consumer.topic.topic1}")
    private String topic;

    public ProducerController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping(value = "send")
    public String send(@RequestParam(name = "msg") String message) {
        kafkaTemplate.send(topic, message);
        return "success";
    }
}
