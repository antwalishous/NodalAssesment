package com.nodal.s1;

import com.nodal.s1.init.StartUpMessanger;
import com.nodal.s1.publisher.RabbitMQPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class S1ServiceTest {
	@MockBean
	private RabbitTemplate rabbitTemplate;

	@MockBean
	private RabbitMQPublisher rabbitMQPublisher;

	@MockBean
	private StartUpMessanger startUpMessanger;


	@Test
	void contextLoads() {
		// This test ensures that the Spring Boot application context starts successfully
	}
}