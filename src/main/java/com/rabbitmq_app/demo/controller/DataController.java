package com.rabbitmq_app.demo.controller;

import com.rabbitmq_app.demo.data.UserDTO;
import com.rabbitmq_app.demo.data.UserResponse;
import com.rabbitmq_app.demo.service.MessageService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DataController {
    @Inject
    private MessageService service;

    //http://localhost:8080/api/v1/create-data
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@NotNull @RequestBody UserDTO user) {
        service.saveTheData(user);
        return ResponseEntity.ok("Json Message sent to RabbitMQ...!");
    }

    @GetMapping("/get-data")
    public ResponseEntity<UserResponse> getJsonMessage() {
        UserResponse response = service.getUser();
        log.info("The controller received {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
