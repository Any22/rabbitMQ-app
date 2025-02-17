package com.rabbitmq_app.demo.service;

import com.rabbitmq_app.demo.data.UserDTO;
import com.rabbitmq_app.demo.data.UserResponse;
import com.rabbitmq_app.demo.entity.UserEntity;
import com.rabbitmq_app.demo.repository.MessageRepository;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageService {
    @Inject
    private MessageRepository messageRepository;

    @Inject
    private RabbitProducer rabbitProducer;


    public void saveTheData(UserDTO userDto) {
        // Send message to RabbitMQ , there DTO will be used by consumer , see consumer
        rabbitProducer.sendMessage(userDto);

    }


    public UserResponse getUser() {
        List<UserEntity> userEntityList = messageRepository.findAll();
        if (userEntityList.isEmpty()) {
            log.info("The repository is empty. Please enter some values");
        }
        log.info("The data received {}", userEntityList);
        List<UserDTO> userDTOList = this.converToDTOList(userEntityList);
        return UserResponse.builder().userList(userDTOList).build();

    }

    private List<UserDTO> converToDTOList(List<UserEntity> userEntityList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity user : userEntityList) {
            UserDTO userDto = new UserDTO();        //creating new object for each entity value
            userDto.setId(user.getUser_id());
            userDto.setFirstName(user.getFirst_name());
            userDto.setLastName(user.getLast_name());
            userDTOList.add(userDto);
        }

        return userDTOList;
    }
}
