package me.hajoo.testcontainers.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.hajoo.testcontainers.entity.User;
import me.hajoo.testcontainers.repository.HelloRepository;

@Service
@RequiredArgsConstructor
public class HelloService {

	private final HelloRepository helloRepository;

	public void save(User user) { helloRepository.save(user); }
	public void findAll() { helloRepository.findAll(); }
}
