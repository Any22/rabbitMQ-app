package com.rabbitmq_app.demo.repository;

import com.rabbitmq_app.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<UserEntity, Integer> {
}
