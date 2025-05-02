package com.nodal.s1.init;

import com.nodal.s1.publisher.RabbitMQPublisher;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StartUpMessangerTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testRun() {
        RabbitMQPublisher mockPublisher = mock(RabbitMQPublisher.class);
        StartUpMessanger startUpMessanger = new StartUpMessanger(mockPublisher);
        startUpMessanger.run();
    }


}
