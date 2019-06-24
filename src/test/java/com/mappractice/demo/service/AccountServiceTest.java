package com.mappractice.demo.service;

import com.mappractice.demo.domain.Account;
import com.mappractice.demo.domain.AccountRepository;
import com.mappractice.demo.dto.AccountReturnDTO;
import com.mappractice.demo.dto.AccountSignUpDTO;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mappractice.demo.acceptanceTest.AcceptanceAccountTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    MockHttpSession session;

    @Test
    public void create_성공() {
        //given
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        //when
        AccountReturnDTO accountReturnDTO = accountService.create(accountSignUpDTO);
        //then
        assertThat(accountReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void delete_성공() {
        //given
        doNothing().when(accountRepository).delete(any(Account.class));
        when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
        //when
        AccountReturnDTO accountReturnDTO = accountService.delete(1l);
        //then
        assertThat(accountReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void update_성공() {
        //given
        AccountSignUpDTO inputedSignUpDTO = AccountSignUpDTO.builder()
                .email("changed@naver.com")
                .name("changedName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        Account originalAccount = new Account("original@test.com", "!Test1234", "originalName");

        Account changedAccount = new Account(
                1l,
                inputedSignUpDTO.getEmail(),
                inputedSignUpDTO.getPassword(),
                inputedSignUpDTO.getName());

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(originalAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(changedAccount);
        //when
        AccountReturnDTO accountReturnDTO = accountService.update(account.getId(), inputedSignUpDTO);
        //then
        assertThat(accountReturnDTO.getName()).isEqualTo("changedName");
    }

    @Test
    public void getList_성공() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        //when
        List<AccountReturnDTO> list = accountService.getList();
        //then
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getName()).isEqualTo("testName");
    }

    @Test
    public void findById_성공() {
        //given
        when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
        //when
        AccountReturnDTO accountReturnDTO = accountService.get(1l);
        //then
        assertThat(accountReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void login_성공() {
        //given
        when(accountRepository.findByEmail(any(String.class))).thenReturn(Optional.of(account));
        //when
        AccountReturnDTO account = accountService.login(session,loginDTO);
        //then
        assertThat(account.getName()).isEqualTo("testName");
    }


    @Test(expected = UnAuthorizedException.class)
    public void login_실패() {
        //given
        LoginDTO loginDTO = new LoginDTO("test@naver.com", "unvalidpassword");
        when(accountRepository.findByEmail(any(String.class))).thenReturn(Optional.of(account));
        //when
        AccountReturnDTO account = accountService.login(session, loginDTO);
    }
}