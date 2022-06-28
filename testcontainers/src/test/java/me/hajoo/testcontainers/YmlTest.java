package me.hajoo.testcontainers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import me.hajoo.testcontainers.entity.User;
import me.hajoo.testcontainers.service.HelloService;

@SpringBootTest
@ActiveProfiles("dev")
public class YmlTest {

	@Autowired
	private HelloService helloService;

	@Test
	@DisplayName("save")
	void save() throws Exception {
		User user = new User("username", "password", "gender", "state", "Y", "19960312");
		helloService.save(user);
	}

	@Test
	@DisplayName("findAll")
	void findAll() throws Exception {
		helloService.findAll();
	}
}