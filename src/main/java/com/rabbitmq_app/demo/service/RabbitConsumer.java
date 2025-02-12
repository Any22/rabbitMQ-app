package com.rabbitmq_app.demo.service;

import com.rabbitmq_app.demo.data.UserDTO;
import com.rabbitmq_app.demo.entity.UserEntity;
import com.rabbitmq_app.demo.repository.MessageRepository;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitConsumer {
    @Inject
    private MessageRepository messageRepository;

    /****************************************************************************************************
     * saveAndFlush() immediately writes data to the database. This is unnecessary unless  specifically
     * need immediate commit.
     * Comsumer should not use the .saveAndFlush() method
     * Instead, use save() (which batches transactions).
     *
     *
     * @param userDto
     ***************************************************************************************************/
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(UserDTO userDto) {
        log.info("Received message-> {}", userDto.toString());
        try {
            UserEntity userEntity = UserEntity
                    .builder()
                    .user_id(userDto.getId())
                    .first_name(userDto.getFirstName())
                    .last_name(userDto.getLastName())
                    .build();
            messageRepository.save(userEntity);
            log.info("The user Entity is saved to database {}:", userEntity);
        } catch (Exception e) {
            log.error(" Error processing the message {}", e.getMessage(), e);
        }
    }


}
