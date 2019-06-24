package com.mappractice.demo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByIdTest() {
        User account = new User(1l, "test@naver.com", "!Test1234", "testName");
        userRepository.save(account);
        Optional<User> byId = userRepository.findById(1l);
        assertThat(byId.get().getName()).isEqualTo("test@naver.com");
    }
}