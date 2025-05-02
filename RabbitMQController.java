package com.nodal.s1.controller;

import com.nodal.s1.publisher.RabbitMQPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitController")
public class RabbitMQController {
    private RabbitMQPublisher publisher;

    public RabbitMQController(RabbitMQPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        publisher.sendMessage(message);
        return ResponseEntity.ok("Message from S1 sent to RabbitMQ");
    }
}
