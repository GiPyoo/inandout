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
    private AccountRepository accountRepository;

    @Test
    public void findByIdTest() {
        User account = new User(1l, "test@naver.com", "!Test1234", "testName");
        accountRepository.save(account);
        Optional<User> byId = accountRepository.findById(1l);
        assertThat(byId.get().getAccount()).isEqualTo("test@naver.com");
    }
}