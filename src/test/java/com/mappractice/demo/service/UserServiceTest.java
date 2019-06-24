package com.mappractice.demo.service;

import com.mappractice.demo.domain.User;
import com.mappractice.demo.domain.AccountRepository;
import com.mappractice.demo.dto.UserReturnDTO;
import com.mappractice.demo.dto.UserSignUpDTO;
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

import static com.mappractice.demo.acceptanceTest.AcceptanceUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    MockHttpSession session;

    @Test
    public void create_성공() {
        //given
        when(accountRepository.save(any(User.class))).thenReturn(account);
        //when
        UserReturnDTO userReturnDTO = userService.create(userSignUpDTO);
        //then
        assertThat(userReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void delete_성공() {
        //given
        doNothing().when(accountRepository).delete(any(User.class));
        when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
        //when
        UserReturnDTO userReturnDTO = userService.delete(1l);
        //then
        assertThat(userReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void update_성공() {
        //given
        UserSignUpDTO inputedSignUpDTO = UserSignUpDTO.builder()
                .account("changed@naver.com")
                .name("changedName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        User originalAccount = new User("original@test.com", "!Test1234", "originalName");

        User changedAccount = new User(
                1l,
                inputedSignUpDTO.getAccount(),
                inputedSignUpDTO.getPassword(),
                inputedSignUpDTO.getName());

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(originalAccount));
        when(accountRepository.save(any(User.class))).thenReturn(changedAccount);
        //when
        UserReturnDTO userReturnDTO = userService.update(account.getId(), inputedSignUpDTO);
        //then
        assertThat(userReturnDTO.getName()).isEqualTo("changedName");
    }

    @Test
    public void getList_성공() {
        List<User> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        //when
        List<UserReturnDTO> list = userService.getList();
        //then
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getName()).isEqualTo("testName");
    }

    @Test
    public void findById_성공() {
        //given
        when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
        //when
        UserReturnDTO userReturnDTO = userService.get(1l);
        //then
        assertThat(userReturnDTO.getName()).isEqualTo("testName");
    }

    @Test
    public void login_성공() {
        //given
        when(accountRepository.findByName(any(String.class))).thenReturn(Optional.of(account));
        //when
        UserReturnDTO account = userService.login(session,loginDTO);
        //then
        assertThat(account.getName()).isEqualTo("testName");
    }


    @Test(expected = UnAuthorizedException.class)
    public void login_실패() {
        //given
        LoginDTO loginDTO = new LoginDTO("test@naver.com", "unvalidpassword");
        when(accountRepository.findByName(any(String.class))).thenReturn(Optional.of(account));
        //when
        UserReturnDTO account = userService.login(session, loginDTO);
    }
}