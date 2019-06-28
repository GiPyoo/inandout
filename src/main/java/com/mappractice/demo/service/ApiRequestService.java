package com.mappractice.demo.service;

import com.mappractice.demo.domain.*;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.hackaton.TransactionHistoryRepository;
import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import com.mappractice.demo.utils.DateUtils;
import com.mappractice.demo.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ApiRequestService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    public void updateHistory(TransactionHistoryResponseDTO transactionHistoryResponseDTO, HttpSession session) {
        List<TransactionHistory> datas = transactionHistoryResponseDTO.getDatas();
        Collections.sort(datas);

        int latestHistoryIndex = checkTime(datas, loadUserLatestTime(session));

        if (latestHistoryIndex == -1)
            return;

        for (int i = latestHistoryIndex; i < datas.size(); i++) {
            transactionHistoryRepository.save(datas.get(i));
            updateAccountHistory(makeAccountHistory(transactionHistoryResponseDTO.getAccount(), datas.get(i)));
        }

        String latestDate = datas.get(datas.size() - 1).getTransactionDate();
        updateUser(session, latestDate);

    }

    private void updateAccountHistory(AccountHistory accountHistory){
        accountHistoryRepository.save(accountHistory);
    }

    private AccountHistory makeAccountHistory(Account account, TransactionHistory history) {

        String tmpAccount = account.getGridInfo();
        VirtualAccount VA = new VirtualAccount();
        LocalDateTime createdAt = LocalDateTime.now();
        int transaction = -1;
        Long deposit = Long.parseLong(history.getOutputCash());
        Long withdraw = Long.parseLong(history.getInputCash());

        if (deposit > 0) //입금 1 출금 0
            transaction = 1;
        else {
            transaction = 0;
        }
        Long amount = Long.parseLong(history.getAmount());

        return new AccountHistory(tmpAccount, VA, createdAt, transaction, deposit, withdraw, amount);
    }

    private void updateUser(HttpSession session, String latestDate) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);

        loginUser.updateLatestTime(latestDate);
        userRepository.save(loginUser);
    }

    private int checkTime(List<TransactionHistory> histories, String userLatestDate) {

        for (int i = 0; i < histories.size(); i++) {
            if (DateUtils.checkNewModification(
                    histories.get(i).getTransactionDate()
                    , userLatestDate)) {
                return i;
            }
        }

        return -1;
    }

    private String loadUserLatestTime(HttpSession session) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);

        return loginUser.getUserLatestTime().toString();
    }
}
