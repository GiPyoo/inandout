package com.mappractice.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
