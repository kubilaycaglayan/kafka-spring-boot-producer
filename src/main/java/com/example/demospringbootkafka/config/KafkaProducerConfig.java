package com.example.demospringbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("new-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic NewUserCreatedTopic() {
        return new NewTopic("joyjoy.users.account.created.1", 3, (short) 1);
    }
}
