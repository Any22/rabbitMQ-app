package com.rabbitmq_app.demo.service;

import com.rabbitmq_app.demo.data.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.inject.Inject;

@Service
@Slf4j
public class RabbitProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Inject
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(UserDTO user) {

        log.info("message sent {}", user.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }

}
