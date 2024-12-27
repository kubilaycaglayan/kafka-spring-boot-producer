package com.example.demospringbootkafka.service;

import com.example.demospringbootkafka.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> send = template.send("new-topic", message);

        send.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(
                        "Sent message = [" + message +
                        "] with offset = [" +
                        result.getRecordMetadata().offset()
                        + "]"
                );
            }
            else {
                System.out.println(
                        "Unable to send message = [" + message + "] :: " +
                        exception.getMessage()
                );
            }
        });
    }

    public void sendUserToTopic(User user) {
        CompletableFuture<SendResult<String, Object>> send = template.send("joyjoy.users.account.created.1", user);

        send.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(
                        "Sent message = [" + user +
                                "] with offset = [" +
                                result.getRecordMetadata().offset()
                                + "]"
                );
            }
            else {
                System.out.println(
                        "Unable to send message = [" + user + "] :: " +
                                exception.getMessage()
                );
            }
        });
    }
}
