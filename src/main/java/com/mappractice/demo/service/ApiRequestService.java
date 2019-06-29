package com.mappractice.demo.service;

import com.mappractice.demo.domain.*;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import com.mappractice.demo.utils.DateUtils;
import com.mappractice.demo.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mappractice.demo.utils.RequestGenerator.getJsonByRestTemplate;

@Service
public class ApiRequestService {

    private final static long category = 1;

    @Autowired
    VirtualAccountRepository virtualAccountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    public void updateLocalTransactionHistory(){
        String hackathonApiRequestURI = "http://localhost:8080/hackathonApi/getAccountTransactionHistory";
        //요청 가져와서
        TransactionHistoryResponseDTO response = getJsonByRestTemplate(hackathonApiRequestURI,
                TransactionHistoryResponseDTO.class);

        //유저판단
        User userByAccountNumber = getUserByAccountNumber(response.getAccount().getAmountNumber());



        // 그 최신시간과 유저 최신시간 비교

        // 최신시간 넘는 애들 업데이트
    }

    private User getUserByAccountNumber(String accountNumber){
        return userRepository.findByAccount(accountNumber).orElseThrow(UnAuthorizedException::new);
    }

    private int getOldestHistoryIndexNeedUpdate(List<TransactionHistory> histories, String userLatestDate) {

        for (int i = 0; i < histories.size(); i++) {
            if (DateUtils.checkNewModification(
                    histories.get(i).getTransactionDate()
                    , userLatestDate)) {
                return i;
            }
        }
        return -1;
    }

    public void updateHistory(TransactionHistoryResponseDTO transactionHistoryResponseDTO, HttpSession session) {
        //TODO : Session 아님, 밖에서 getUserByAccountNumber 메서드를 통해서 User 객체로 입력받겠음.
        List<TransactionHistory> datas = transactionHistoryResponseDTO.getDatas();
        Collections.sort(datas);

        int latestHistoryIndex = getOldestHistoryIndexNeedUpdate(datas, loadUserLatestTime(session));

        if (latestHistoryIndex == -1)
            return;

        for (int i = latestHistoryIndex; i < datas.size(); i++) {
            updateAccountHistory(makeAccountHistory(transactionHistoryResponseDTO.getAccount(), datas.get(i)));
        }
        String latestDate = datas.get(datas.size() - 1).getTransactionDate();
        updateUser(session, latestDate);
    }

    private void updateAccountHistory(AccountHistory accountHistory) {
        accountHistoryRepository.save(accountHistory);
    }

    private AccountHistory makeAccountHistory(Account account, TransactionHistory history) {

        int transaction = -1;

        LocalDateTime createdAt = LocalDateTime.parse(history.getTransactionDate());
        ;
        String tmpAccount = account.getGridInfo();
        Long deposit = Long.parseLong(history.getOutputCash());
        Long withdraw = Long.parseLong(history.getInputCash());

        if (deposit > 0) //입금 0 출금 1
            transaction = 0;
        else {
            transaction = 1;
        }
        Long amount = Long.parseLong(history.getAmount());

        Long categoryId = 0l;

        //카테고리 판단
        // if category 가 나올경우 categoryId = 리턴값으로

        return new AccountHistory(tmpAccount, findVirtualAccount(account, categoryId), createdAt, transaction, deposit, withdraw, amount);
    }

    private VirtualAccount findVirtualAccount(Account account, long category) {
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAllByUserId(account.getId()); //사용자가 가지고 있는 가상계좌들
        int zero = -1;

        List<Long> index = new ArrayList<>();
        for (int i = 0; i < virtualAccountList.size(); i++) {
            index.add(virtualAccountList.get(i).getCategory().getId());
        }

        for (int i = 0; i < index.size(); i++) {
            if (index.get(i) == 0)
                zero = i;

            if (index.get(i) == category) {
                return virtualAccountList.get(i);
            }
        }

        if (zero == -1) {
            return null;
        }

        return virtualAccountList.get(zero);
    }

    private void updateUser(HttpSession session, String latestDate) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);

        loginUser.updateLatestTime(latestDate);
        userRepository.save(loginUser);
    }

    private String loadUserLatestTime(HttpSession session) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);

        return loginUser.getUserLatestTime().toString();
    }
}
