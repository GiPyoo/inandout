package com.mappractice.demo.service;

import com.mappractice.demo.domain.AccountHistory;
import com.mappractice.demo.domain.AccountHistoryRepository;
import com.mappractice.demo.domain.VirtualAccount;
import com.mappractice.demo.domain.VirtualAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// 구현 미완료!!!!!
@Service
public class AccountService {

    @Autowired
    VirtualAccountRepository virtualAccountRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    public void paymentProcess(VirtualAccount from, Long money) {
        withdrawalProcess(from, money);
    }

    //가상계좌간의 이체
    public void transferProcess(VirtualAccount from, VirtualAccount to, Long money) {
        withdrawalProcess(from, money);
        depositProcess(to, money);
    }

    private void withdrawalProcess(VirtualAccount from, Long money) {
        long virtualAccountAmount = from.getAmount();
        virtualAccountAmount -= money;
        from.setAmount(virtualAccountAmount);
        makeProcessHistory(from);
    }

    private void depositProcess(VirtualAccount to, Long money) {
        long virtual_account_amount = to.getAmount();
        virtual_account_amount += money;
        to.setAmount(virtual_account_amount);
        makeProcessHistory(to);
    }

    private void makeProcessHistory(VirtualAccount virtualAccount) {
        virtualAccountRepository.save(virtualAccount);
    }

    // private void make

}