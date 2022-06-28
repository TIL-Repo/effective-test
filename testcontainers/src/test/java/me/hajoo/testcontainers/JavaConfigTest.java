package me.hajoo.testcontainers;

import me.hajoo.testcontainers.entity.User;
import me.hajoo.testcontainers.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class JavaConfigTest {

    @Autowired
    private HelloService helloService;

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.24")
            .withInitScript("schema.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

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
