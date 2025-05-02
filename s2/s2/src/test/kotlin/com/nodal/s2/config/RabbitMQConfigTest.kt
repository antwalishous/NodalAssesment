package com.nodal.s2.config

import com.nodal.s2.publisher.RabbitMQPublisherTest
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@Import(RabbitMQConfig::class)
@TestPropertySource(
    properties = ["rabbitmq.queue.s2.send=test.s2.send"
        , "rabbitmq.queue.s2.response=test.s2.response"
        , "rabbitmq.queue.s1.send=test.s1.send"
        , "rabbitmq.queue.s1.response=test.s1.response"
        , "rabbitmq.exchange.name=test.exchange"
        , "rabbitmq.routing.key.s1.send=test.s1.send.key"
        , "rabbitmq.routing.key.s1.response=test.s1.response.key"
        , "rabbitmq.routing.key.s2.send=test.s2.send.key"
        , "rabbitmq.routing.key.s2.response=test.s2.response.key"
    ]
)
internal class RabbitMQConfigTest {
    @Autowired
    private val config: RabbitMQConfig? = null

    @MockBean
    private val rabbitTemplate: RabbitTemplate? = null

    @MockBean
    private val rabbitMQPublisher: RabbitMQPublisherTest? = null

    @Test
    fun testQueueBeans() {
        val queue1 = config!!.sends2Queue()
        val queue2 = config.responds2Queue()
        val queue3 = config.sends1Queue()
        val queue4 = config.responds1Queue()

        AssertionsForClassTypes.assertThat(queue1.getName()).isEqualTo("test.s2.send")
        AssertionsForClassTypes.assertThat(queue2.getName()).isEqualTo("test.s2.response")
        AssertionsForClassTypes.assertThat(queue3.getName()).isEqualTo("test.s1.send")
        AssertionsForClassTypes.assertThat(queue4.getName()).isEqualTo("test.s1.response")
    }

    @Test
    fun testExchangeBean() {
        val exchange = config!!.exchange()
        AssertionsForClassTypes.assertThat(exchange.getName()).isEqualTo("test.exchange")
    }

    @Test
    fun testBindings() {
        val binding1 = config!!.sendS1Binding()
        val binding2 = config.sendS2Binding()
        val binding3 = config.responseS1binding()
        val binding4 = config.responseS2binding()

        AssertionsForClassTypes.assertThat(binding1.getRoutingKey()).isEqualTo("test.s1.send.key")
        AssertionsForClassTypes.assertThat(binding2.getRoutingKey()).isEqualTo("test.s2.send.key")
        AssertionsForClassTypes.assertThat(binding3.getRoutingKey()).isEqualTo("test.s1.response.key")
        AssertionsForClassTypes.assertThat(binding4.getRoutingKey()).isEqualTo("test.s2.response.key")
    }
}