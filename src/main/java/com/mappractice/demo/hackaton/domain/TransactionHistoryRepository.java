package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
