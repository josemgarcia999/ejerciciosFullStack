package com.example.EJKA1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerTest {

    @KafkaListener(topics = "${message.topic.name:kafkatopic}", groupId = "${message.group.name:kafkagroup}")
    public void listenTopic1(String message) {
        System.out.println("Recieved Message of topic1 in  listener: " + message);
    }

    @KafkaListener(topics = "${message.topic.name2:kafkatopic2}", groupId = "${message.group.name:kafkagroup2}")
    public void listenTopic2(String message) {
        System.out.println("Recieved Message of topic2 in  listener "+message);
    }
}