package me.hajoo.testcontainers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hajoo.testcontainers.entity.User;

public interface HelloRepository extends JpaRepository<User, Long> {
}
