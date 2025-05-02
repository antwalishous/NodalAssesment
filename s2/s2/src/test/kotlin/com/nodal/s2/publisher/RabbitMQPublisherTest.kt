package com.nodal.s2.publisher

import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class RabbitMQPublisherTest {
    @MockBean
    private val rabbitTemplate: RabbitTemplate? = null

    @Autowired
    private lateinit var publisher: RabbitMQPublisher

    @Test
    fun testsendMessage() {
        publisher.sendMessage("Ping")
    }
}