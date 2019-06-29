package com.mappractice.demo.service;

import com.mappractice.demo.domain.AccountHistory;
import com.mappractice.demo.domain.AccountHistoryRepository;
import com.mappractice.demo.domain.VirtualAccount;
import com.mappractice.demo.domain.VirtualAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountHistoryService {

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private VirtualAccountRepository virtualAccountRepository;


    public List<AccountHistory> getHistories(Long accountId) {
        VirtualAccount virtualAccount = virtualAccountRepository.findById(accountId).orElseThrow(RuntimeException::new);

        return accountHistoryRepository.findAll().stream()
                .filter(accountHistory -> accountHistory.getVirtualAccount().equals(virtualAccount)).collect(Collectors.toList());
    }
}
