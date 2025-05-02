package com.nodal.s2

import com.nodal.s2.publisher.RabbitMQPublisherTest
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class S1ApplicationTest {
	@MockBean
	private val rabbitTemplate: RabbitTemplate? = null

	@MockBean
	private val rabbitMQPublisher: RabbitMQPublisherTest? = null

	@Test
	fun contextLoads() {
		// This test ensures that the Spring Boot application context starts successfully
	}
}
