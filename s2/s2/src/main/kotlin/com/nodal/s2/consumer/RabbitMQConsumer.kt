package com.nodal.s2.consumer

import com.nodal.s2.publisher.RabbitMQPublisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer {
    @Value("\${rabbitmq.routing.key.s2.response}")
    private lateinit var respondS2RoutingKey: String

    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchange: String

    val LOGGER: Logger = LoggerFactory.getLogger(RabbitMQConsumer::class.java)

    private var rabbitTemplate: RabbitTemplate

    constructor(rabbitTemplate: RabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate
    }

    @Autowired
    private val rabbitMQPublisher: RabbitMQPublisher? = null

    @RabbitListener(queues = ["\${rabbitmq.queue.s2.send}"])
    fun consume(message: String?) {

        LOGGER.info("S2 received following message from S1: {}", message)
        if("Ping" == message) {
            rabbitTemplate.convertAndSend(exchange, respondS2RoutingKey, "Pong")

            try {
                Thread.sleep(10000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }

            LOGGER.info("S2 sending 'Ping' to S1")
            rabbitMQPublisher!!.sendMessage("Ping")
        }
        logSplitter()
    }

    @RabbitListener(queues = ["\${rabbitmq.queue.s1.response}"])
    fun consumeResponse(message: String?) {
        //publishPong after receiving Ping from s2

        LOGGER.info("S2 received following Response message from S1: {}", message)
        logSplitter()
    }

    private fun logSplitter() {
        LOGGER.info("---------------------------------------------------------")
    }

}