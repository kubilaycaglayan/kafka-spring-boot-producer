package com.example.demospringbootkafka.controller;

import com.example.demospringbootkafka.DTO.User;
import com.example.demospringbootkafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i < 10_000_000; i++) {
                publisher.sendMessageToTopic(message + " (" + i + ")");
            }

            return ResponseEntity.ok("Message published successfully");
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishUser(@RequestBody User user) {
        try {
            for (int i = 0; i < 5; i++) {
                publisher.sendUserToTopic(user);
            }

            return ResponseEntity.ok("User published successfully");
        }
        catch (Exception exception) {
            // log
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
