package com.example.avro.producer;

import com.example.avro.schema.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class UserProducer {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user){
        CompletableFuture<SendResult<String, User>> result = kafkaTemplate.send(topicName, user.getName(), user);
        result.whenComplete((stringUserSendResult, throwable) -> {
            if (Objects.nonNull(throwable)) {
                log.error("Exception occurred: " + throwable.getMessage());
            }else{
                log.info("The avro message has been sent successfully");
            }
        });
    }
}
