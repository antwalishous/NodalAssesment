package com.nodal.s1.config;

import com.nodal.s1.init.StartUpMessanger;
import com.nodal.s1.publisher.RabbitMQPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(RabbitMQConfig.class)
@TestPropertySource(properties = {
        "rabbitmq.queue.s2.send=test.s2.send",
        "rabbitmq.queue.s2.response=test.s2.response",
        "rabbitmq.queue.s1.send=test.s1.send",
        "rabbitmq.queue.s1.response=test.s1.response",
        "rabbitmq.exchange.name=test.exchange",
        "rabbitmq.routing.key.s1.send=test.s1.send.key",
        "rabbitmq.routing.key.s1.response=test.s1.response.key",
        "rabbitmq.routing.key.s2.send=test.s2.send.key",
        "rabbitmq.routing.key.s2.response=test.s2.response.key"
})
class RabbitMQConfigTest {

    @Autowired
    private RabbitMQConfig config;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private RabbitMQPublisher rabbitMQPublisher;

    @MockBean
    private StartUpMessanger startUpMessanger;

    @Test
    void testQueueBeans() {
        Queue queue1 = config.sends2Queue();
        Queue queue2 = config.responds2Queue();
        Queue queue3 = config.sends1Queue();
        Queue queue4 = config.responds1Queue();

        assertThat(queue1.getName()).isEqualTo("test.s2.send");
        assertThat(queue2.getName()).isEqualTo("test.s2.response");
        assertThat(queue3.getName()).isEqualTo("test.s1.send");
        assertThat(queue4.getName()).isEqualTo("test.s1.response");
    }

    @Test
    void testExchangeBean() {
        TopicExchange exchange = config.exchange();
        assertThat(exchange.getName()).isEqualTo("test.exchange");
    }

    @Test
    void testBindings() {
        Binding binding1 = config.sendS1Binding();
        Binding binding2 = config.sendS2Binding();
        Binding binding3 = config.responseS1binding();
        Binding binding4 = config.responseS2binding();

        assertThat(binding1.getRoutingKey()).isEqualTo("test.s1.send.key");
        assertThat(binding2.getRoutingKey()).isEqualTo("test.s2.send.key");
        assertThat(binding3.getRoutingKey()).isEqualTo("test.s1.response.key");
        assertThat(binding4.getRoutingKey()).isEqualTo("test.s2.response.key");
    }

}
