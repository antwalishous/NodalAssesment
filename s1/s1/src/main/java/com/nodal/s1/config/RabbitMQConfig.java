package com.nodal.s1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.s2.send}")
    private String sends2Queue;
    @Value("${rabbitmq.queue.s2.response}")
    private String responds2Queue;

    @Value("${rabbitmq.queue.s1.send}")
    private String sends1Queue;
    @Value("${rabbitmq.queue.s1.response}")
    private String responds1Queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.s1.send}")
    private String sendS1RoutingKey;

    @Value("${rabbitmq.routing.key.s1.response}")
    private String respondS1RoutingKey;

    @Value("${rabbitmq.routing.key.s2.send}")
    private String sendS2RoutingKey;

    @Value("${rabbitmq.routing.key.s2.response}")
    private String respondS2RoutingKey;

    @Bean
    public Queue sends2Queue(){
        return new Queue(sends2Queue);
    }

    @Bean
    public Queue responds2Queue(){
        return new Queue(responds2Queue);
    }

    @Bean
    public Queue sends1Queue(){
        return new Queue(sends1Queue,true);
    }

    @Bean
    public Queue responds1Queue(){
        return new Queue(responds1Queue,true);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange,true,false);
    }
    @Bean
    public Binding sendS1Binding(){
        return BindingBuilder
                .bind(sends1Queue())
                .to(exchange())
                .with(sendS1RoutingKey);
    }

    @Bean
    public Binding sendS2Binding(){
        return BindingBuilder
                .bind(sends2Queue())
                .to(exchange())
                .with(sendS2RoutingKey);
    }

    @Bean
    public Binding responseS1binding(){
        return BindingBuilder
                .bind(responds1Queue())
                .to(exchange())
                .with(respondS1RoutingKey);
    }
    @Bean
    public Binding responseS2binding(){
        return BindingBuilder
                .bind(responds2Queue())
                .to(exchange())
                .with(respondS2RoutingKey);
    }
}
