package com.mappractice.demo.hackaton.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
