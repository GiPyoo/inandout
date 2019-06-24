package com.mappractice.demo.service;

import com.mappractice.demo.domain.Account;
import com.mappractice.demo.domain.AccountRepository;
import com.mappractice.demo.dto.AccountReturnDTO;
import com.mappractice.demo.dto.AccountSignUpDTO;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountReturnDTO create(AccountSignUpDTO accountSignUpDTO) {
        if (!accountSignUpDTO.passwordConfirm()) {
            throw new UnAuthorizedException("unmatch password");
        }
        Account savedAccount = accountRepository.save(accountSignUpDTO.toEntity());
        return new AccountReturnDTO(savedAccount);
    }

    public AccountReturnDTO delete(Long id) {
        Account account = findById(id);
        accountRepository.delete(account);
        return new AccountReturnDTO(account);
    }

    public AccountReturnDTO update(Long id, AccountSignUpDTO accountSignUpDTO) {
        Account foundAccount = accountRepository.findById(id)
                .filter(account -> account.matchPassword(accountSignUpDTO.getPassword()))
                .orElseThrow(UnAuthorizedException::new);
        foundAccount.update(accountSignUpDTO);
        return new AccountReturnDTO(accountRepository.save(foundAccount));
    }

    public List<AccountReturnDTO> getList() {
        return accountRepository.findAll().stream()
                .map(AccountReturnDTO::new)
                .collect(Collectors.toList());
    }

    public AccountReturnDTO get(Long id) {
        return new AccountReturnDTO(findById(id));
    }

    private Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public AccountReturnDTO login(HttpSession session, LoginDTO loginDTO) {
        Account account = accountRepository.findByEmail(loginDTO.getEmail()).orElseThrow(UnAuthorizedException::new);
        if(!account.matchPassword(loginDTO.getPassword())){
            throw new UnAuthorizedException("unvalid password");
        }
        session.setAttribute(SessionUtils.USER_SESSION_KEY, account);
        return new AccountReturnDTO(account);
    }
}
