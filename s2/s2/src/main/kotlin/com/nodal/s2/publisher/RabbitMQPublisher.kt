package com.nodal.s2.publisher

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitMQPublisher {

    val LOGGER: Logger = LoggerFactory.getLogger(RabbitMQPublisher::class.java)

    @Value("\${rabbitmq.queue.s1.send}")
    private val sendQueue: String? = null

    @Value("\${rabbitmq.exchange.name}")
    private val exchange: String? = null

    @Value("\${rabbitmq.routing.key.s1.send}")
    private val sendS1RoutingKey: String? = null

    private var rabbitTemplate: RabbitTemplate? = null

    @Autowired
    fun RabbitMQPublisher(rabbitTemplate: RabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate
    }

    fun sendMessage(message: String) {
        LOGGER.info("Message Sent by S2: {}", message)
        //!! -> not null assertion
        rabbitTemplate!!.convertAndSend(exchange!!, sendS1RoutingKey!!, message)
    }
}