package com.lc.frame.kafka.demo.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TopicTestSink {
    String INPUT = "topic-test-input";
    String OUTPUT = "topic-test-output";
    @Input(INPUT)
    SubscribableChannel topicTestInput();
    @Output(OUTPUT)
    MessageChannel topicTestOutput();
}
