package com.mappractice.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, Long> {

    Optional<VirtualAccount> findByUser(User user);

    List<VirtualAccount> findAllByUserId(Long userId);

    Optional<VirtualAccount> findByCategoryId(long categoryId);
}
