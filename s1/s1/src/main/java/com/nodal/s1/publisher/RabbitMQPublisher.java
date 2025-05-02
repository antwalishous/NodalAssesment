package com.nodal.s1.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPublisher.class);

    @Value("${rabbitmq.queue.s2.send}")
    private String sendQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.s2.send}")
    private String sendS2RoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info("Message Sent by S1 to rqm: {}", message);
        rabbitTemplate.convertAndSend(exchange,sendS2RoutingKey,message);
    }

}
