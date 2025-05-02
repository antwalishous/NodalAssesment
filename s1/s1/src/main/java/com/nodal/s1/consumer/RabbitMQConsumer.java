package com.nodal.s1.consumer;

import com.nodal.s1.publisher.RabbitMQPublisher;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Value("${rabbitmq.routing.key.s1.response}")
    private String respondS1RoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    @RabbitListener(queues = {"${rabbitmq.queue.s1.send}"})
    public void consumeSend(String message){
        //publishPong after receiving Ping from s2

        LOGGER.info("S1 received following message from S2: {}", message);
        if("Ping".equals(message)) {
            rabbitTemplate.convertAndSend(exchange, respondS1RoutingKey, "Pong");
            LOGGER.info("S1 sends response with message: Pong");
            //wait 10 seconds
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            LOGGER.info("S1 sending 'Ping' to S2");
            rabbitMQPublisher.sendMessage("Ping");
        }
        logSplitter();
    }

    @RabbitListener(queues = {"${rabbitmq.queue.s2.response}"})
    public void consumeResponse(String message){
        //publishPong after receiving Ping from s2
        LOGGER.info("S1 received following Response message S2: {}", message);
        logSplitter();
    }

    private void logSplitter(){
        LOGGER.info("---------------------------------------------------------");
    }
}
