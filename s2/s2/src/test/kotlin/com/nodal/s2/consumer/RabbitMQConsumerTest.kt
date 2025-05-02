package com.nodal.s2.consumer

import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class RabbitMQConsumerTest {
    @MockBean
    private val rabbitTemplate: RabbitTemplate? = null

    @Autowired
    private lateinit var consumer: RabbitMQConsumer

    @Test
    fun testConsumeResponse() {
        consumer.consumeResponse("Pong")
    }

    @Test
    fun testConsumeSend() {
        consumer.consume("Ping")
    }
}