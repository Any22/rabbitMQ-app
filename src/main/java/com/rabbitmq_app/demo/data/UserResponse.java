package com.rabbitmq_app.demo.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private List<UserDTO> userList;
}

