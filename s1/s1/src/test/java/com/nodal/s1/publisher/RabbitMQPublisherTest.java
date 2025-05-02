package com.nodal.s1.publisher;

import com.nodal.s1.consumer.RabbitMQConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class RabbitMQPublisherTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQPublisher publisher;

    @Test
    public void testsendMessage(){
        publisher.sendMessage("Ping");
    }

}
