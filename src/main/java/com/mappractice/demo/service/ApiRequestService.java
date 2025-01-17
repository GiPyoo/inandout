package com.mappractice.demo.service;

import com.mappractice.demo.domain.*;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import com.mappractice.demo.utils.DateUtils;
import com.mappractice.demo.utils.FakeMachineLearning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.mappractice.demo.utils.RequestGenerator.getJsonByRestTemplate;
import static com.mappractice.demo.utils.SessionUtils.isLoginUser;

@Service
public class ApiRequestService {

    @Autowired
    VirtualAccountRepository virtualAccountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Transactional
    public void updateLocalTransactionHistory(HttpSession httpSession) {
        String hackathonApiRequestURI = "http://localhost:8080/hackathonApi/getAccountTransactionHistory";
        //요청 가져와서
        TransactionHistoryResponseDTO response = getJsonByRestTemplate(hackathonApiRequestURI,
                TransactionHistoryResponseDTO.class);

        //유저판단 + 로그인 유저와 일치하는지
        User user = getLoginUserByAccountNumber(httpSession, response.getAccount().getAccountNumber());

        // 그 최신시간과 유저 최신시간 비교
        List<TransactionHistory> apiHistories = response.getDatas();
        String latestJoinTime = user.getUserLatestTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        int index = getFirstIndexNeedUpdate(apiHistories, latestJoinTime);

        // 최신시간 넘는 애들 업데이트
        updateHistories(apiHistories, index, user);

        //유저 업데이터
        updateLatestJoinTime(user);
    }

    private User getLoginUserByAccountNumber(HttpSession httpSession, String accountNumber) {
        User accountOwner = userRepository.findByAccount(accountNumber).orElseThrow(UnAuthorizedException::new);
        if (isLoginUser(httpSession, accountOwner)) {
            return accountOwner;
        }
        throw new UnAuthorizedException();
    }

    private int getFirstIndexNeedUpdate(List<TransactionHistory> histories, String userLatestDate) {

        for (int i = 0; i < histories.size(); i++) {
            if (DateUtils.checkNewModification(
                    histories.get(i).getTransactionDate().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                    , userLatestDate)) {
                return i;
            }
        }
        return -1;
    }


    private void updateHistories(List<TransactionHistory> apiHistories, int updateStartIndex, User user) {
        if (updateStartIndex == -1) {
            return;
        }
        for (int i = updateStartIndex; i < apiHistories.size(); i++) {
            updateAccountHistory(makeAccountHistory(apiHistories.get(i), user));
        }
    }

    private void updateAccountHistory(AccountHistory accountHistory) {
        accountHistoryRepository.save(accountHistory);
    }

    private void reflectVirtualAccount(AccountHistory accountHistory) {
        VirtualAccount virtualAccount = accountHistory.getVirtualAccount();
        virtualAccount.reflectHistory(accountHistory);

        virtualAccountRepository.save(virtualAccount);
    }

    private AccountHistory makeAccountHistory(TransactionHistory apiHistory, User user) {

        int transaction = -1;
        LocalDateTime createdAt = apiHistory.getTransactionDate();

        Long deposit = Long.parseLong(apiHistory.getInputCash());
        Long withdraw = Long.parseLong(apiHistory.getOutputCash());
        String name = apiHistory.getClient();

        long moneyChange = deposit - withdraw;
        //Long totleAmount = Long.parseLong(apiHistory.getAmount());

        Long categoryId = FakeMachineLearning.findMachineLearning(apiHistory.getClient());

        //카테고리 판단
        // if category 가 나올경우 categoryId = 리턴값으로
        VirtualAccount targetVirtualAccount = findVirtualAccount(user, categoryId);
        targetVirtualAccount.depositMoney(moneyChange);

        return new AccountHistory(name, targetVirtualAccount, createdAt, transaction,
                deposit, withdraw, targetVirtualAccount.getAmount());
    }

    private VirtualAccount findVirtualAccount(User user, long category) {
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAllByUserId(user.getId()); //사용자가 가지고 있는 가상계좌들
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

    private void updateLatestJoinTime(User user) {
        user.setUserLatestTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
