package com.mappractice.demo.service;

import com.mappractice.demo.domain.*;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class VirtualAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VirtualAccountRepository virtualAccountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    public List<VirtualAccount> getList(HttpSession session) {
        return userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new).getVirtualAccounts();
    }

    public void create(HttpSession session, Long categoryId) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);
        VirtualAccount account = new VirtualAccount();
        account.setUser(loginUser);
        account.setCategory(categoryRepository.findById(categoryId).orElseThrow(RuntimeException::new));

        loginUser.getVirtualAccounts().add(account);
        userRepository.save(loginUser);

        virtualAccountRepository.save(account);
    }


    public void update(HttpSession session, Long accountId) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);
        List<VirtualAccount> accounts = loginUser.getVirtualAccounts();

        VirtualAccount foundAccount = accounts.stream().filter(account -> account.isSameId(accountId)).findFirst().orElseThrow(RuntimeException::new);

        // 뭘 수정하나..

        virtualAccountRepository.save(foundAccount);
    }

    public VirtualAccount getAccount(Long accountId) {
        return virtualAccountRepository.findById(accountId).orElseThrow(RuntimeException::new);
    }

    public void delete(HttpSession session, Long accountId) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);
        List<VirtualAccount> accounts = loginUser.getVirtualAccounts();

        accounts.stream().filter(account -> account.isSameId(accountId))
                .forEach(account -> virtualAccountRepository.delete(account));

        accounts.stream().filter(account -> account.isSameId(accountId))
                .forEach(accounts::remove);

        loginUser.setVirtualAccounts(accounts);
        userRepository.save(loginUser);
    }

    public void deposit(HttpSession session, Long accountId, Long money) {
        User loginUser = userRepository.findByName(SessionUtils.getLoginUser(session).getName()).orElseThrow(UnAuthorizedException::new);
        VirtualAccount account = virtualAccountRepository.findById(accountId).orElseThrow(RuntimeException::new);

        transferMoney(loginUser, account, virtualAccountRepository.findById(0l).orElseThrow(RuntimeException::new), money);
    }

    private void transferMoney(User loginUser, VirtualAccount to, VirtualAccount from, Long money) {
        AccountHistory depositHistory = createAccountHistory(loginUser.getAccount(), to, money, AccountHistory.DEPOSIT_CODE);
        AccountHistory withdrawHistory = createAccountHistory(loginUser.getAccount(), from, money, AccountHistory.WITHDRAW_CODE);

        adjustAccount(depositHistory);
        adjustAccount(withdrawHistory);
    }

    private void adjustAccount(AccountHistory history) {
        VirtualAccount account = history.getVirtualAccount();
        account.updateAmount(history.getAmount());

        virtualAccountRepository.save(account);
    }

    private AccountHistory createAccountHistory(String account, VirtualAccount virtualAccount, Long money, int transactionType) {
        AccountHistory history = new AccountHistory();

        if (transactionType == AccountHistory.DEPOSIT_CODE) {
            history.createDeposit(account, virtualAccount, money);
        }
        if (transactionType == AccountHistory.WITHDRAW_CODE) {
            history.createWithDraw(account, virtualAccount, money);
        }

        return accountHistoryRepository.save(history);
    }
}
