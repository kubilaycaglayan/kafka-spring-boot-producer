package com.example.demospringbootkafka;

import com.example.demospringbootkafka.DTO.User;
import com.example.demospringbootkafka.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DemoSpringBootKafkaApplicationTests {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("apache/kafka-native:3.8.0"));

    @Test
    void contextLoads() {
        assert true;
    }

    @DynamicPropertySource
    public static void initKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaMessagePublisher publisher;

    @Test
    public void sendEventsToTopic() {
        publisher.sendUserToTopic(new User(1, "John Doe", "asd", "a@a.com"));
        await().pollInterval(Duration.ofSeconds(1)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            // assert statement
        });
    }

}
