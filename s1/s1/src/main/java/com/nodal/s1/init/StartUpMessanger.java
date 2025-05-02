package com.nodal.s1.init;

import com.nodal.s1.publisher.RabbitMQPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUpMessanger implements CommandLineRunner {
        private final RabbitMQPublisher publisher;
        private static final Logger LOGGER = LoggerFactory.getLogger(StartUpMessanger.class);
        public StartUpMessanger(RabbitMQPublisher publisher) {
            this.publisher = publisher;
        }

        @Override
        public void run(String... args) {
            logSplitter();
            LOGGER.info("Message from S1 sent on app startup");
            publisher.sendMessage("Ping");
            logSplitter();
        }

    private void logSplitter(){
        LOGGER.info("---------------------------------------------------------");
    }
}
