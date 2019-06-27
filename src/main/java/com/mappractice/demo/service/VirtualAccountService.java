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
}
