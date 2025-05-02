package com.nodal.s2.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Value("\${rabbitmq.queue.s2.send}")
    private lateinit var sends2Queue: String

    @Value("\${rabbitmq.queue.s2.response}")
    private lateinit var responds2Queue: String

    @Value("\${rabbitmq.queue.s1.send}")
    private lateinit var sends1Queue: String

    @Value("\${rabbitmq.queue.s1.response}")
    private lateinit var responds1Queue: String

    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchange: String

    @Value("\${rabbitmq.routing.key.s1.send}")
    private lateinit var sendS1RoutingKey: String

    @Value("\${rabbitmq.routing.key.s1.response}")
    private lateinit var respondS1RoutingKey: String

    @Value("\${rabbitmq.routing.key.s2.send}")
    private lateinit var sendS2RoutingKey: String

    @Value("\${rabbitmq.routing.key.s2.response}")
    private lateinit var respondS2RoutingKey: String

    @Bean
    fun sends2Queue(): Queue {
        return Queue(sends2Queue)
    }

    @Bean fun responds2Queue(): Queue{
        return Queue(responds2Queue)
    }
    @Bean
    fun sends1Queue(): Queue {
        return Queue(sends1Queue)
    }

    @Bean fun responds1Queue(): Queue{
        return Queue(responds1Queue)
    }

    @Bean fun exchange(): TopicExchange {
        return TopicExchange(exchange,true,false)
    }

    @Bean
    fun sendS1Binding(): Binding {
        return BindingBuilder
            .bind(sends1Queue())
            .to(exchange())
            .with(sendS1RoutingKey)
    }

    @Bean
    fun sendS2Binding(): Binding {
        return BindingBuilder
            .bind(sends2Queue())
            .to(exchange())
            .with(sendS2RoutingKey)
    }

    @Bean
    fun responseS1binding(): Binding {
        return BindingBuilder
            .bind(responds1Queue())
            .to(exchange())
            .with(respondS1RoutingKey)
    }

    @Bean
    fun responseS2binding(): Binding {
        return BindingBuilder
            .bind(responds2Queue())
            .to(exchange())
            .with(respondS2RoutingKey)
    }
}