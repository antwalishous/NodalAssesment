package com.nodal.s1.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class RabbitMQConsumerTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConsumer consumer;

    @Test
    public void testConsumeResponse(){
        consumer.consumeResponse("Pong");
    }

    @Test
    public void testConsumeSend(){
        consumer.consumeSend("Ping");
    }
}
