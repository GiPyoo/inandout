package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.ApiAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAccountRepository extends JpaRepository<ApiAccount, Long> {
}
