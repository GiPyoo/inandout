package com.mappractice.demo.acceptanceTest;

import com.mappractice.demo.domain.Account;
import com.mappractice.demo.domain.AccountRepository;
import com.mappractice.demo.dto.AccountReturnDTO;
import com.mappractice.demo.dto.AccountSignUpDTO;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.response.RestResponse;
import com.mappractice.demo.support.AcceptanceTest;
import com.mappractice.demo.web.ApiAccountController;
import com.mappractice.demo.web.UriResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceAccountTest extends AcceptanceTest {
    private final Logger log = LoggerFactory.getLogger(AcceptanceAccountTest.class);
    private final String ACCOUNT_URI = UriResource.ACCOUNTS_V1_URI;

    @Autowired
    private ApiAccountController apiAccountController;

    @Autowired
    private AccountRepository accountRepository;

    public static AccountSignUpDTO accountSignUpDTO;
    public static Account account;
    public static AccountReturnDTO accountReturnDTO;
    public static LoginDTO loginDTO;

    static {
        accountSignUpDTO = AccountSignUpDTO.builder()
                .email("test@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        account = new Account(2l, "test@naver.com", "!Test1234", "testName");
        accountReturnDTO = new AccountReturnDTO(2l, "test@naver.com", "testName");
        loginDTO = new LoginDTO("test@naver.com", "!Test1234");
    }

    @Test
    public void read_list_성공() {
        ResponseEntity<RestResponse> responseEntity = sendGet(ACCOUNT_URI, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void get_user_성공() {
        AccountSignUpDTO accountDTO = AccountSignUpDTO.builder()
                .email("test3@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        String resourceURI = createResource(ACCOUNT_URI, accountDTO);

        ResponseEntity<RestResponse> responseEntity = sendGet(resourceURI, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void post_login_성공() {
        AccountSignUpDTO accountDTO = AccountSignUpDTO.builder()
                .email("test4@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        String resourceURI = createResource(ACCOUNT_URI, accountDTO);

        LoginDTO loginDTO3 = new LoginDTO("test4@naver.com", "!Test1234");

        ResponseEntity<RestResponse> responseEntity = sendPost(ACCOUNT_URI+"/login" ,loginDTO3, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void create_성공() {
        AccountSignUpDTO accountDTO = AccountSignUpDTO.builder()
                .email("test1@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        ResponseEntity<RestResponse> responseEntity = sendPost(ACCOUNT_URI, accountDTO, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountRepository.findByEmail(accountDTO.getEmail()).isPresent()).isEqualTo(true);
    }

    @Test
    public void update_성공() {
        AccountSignUpDTO accountDTO = AccountSignUpDTO.builder()
                .email("test2@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        String resourceURI = createResource(ACCOUNT_URI, accountDTO);

        AccountSignUpDTO changedAccountDTO = AccountSignUpDTO.builder()
                .email("test3@naver.com")
                .name("changedName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        ResponseEntity<RestResponse> responseEntity = sendPut(resourceURI, changedAccountDTO, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountRepository.findByEmail(changedAccountDTO.getEmail()).get().getName()).isEqualTo("changedName");
    }

    @Test
    public void delete_성공() {
        AccountSignUpDTO accountDTO = AccountSignUpDTO.builder()
                .email("test2@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        String resourceURI = createResource(ACCOUNT_URI, accountDTO);
        ResponseEntity<RestResponse> responseEntity = sendDelete(resourceURI, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountRepository.findByEmail(accountDTO.getEmail()).isPresent()).isEqualTo(false);
    }

    protected String createResource(String uri, AccountSignUpDTO accountDTO) {
        ResponseEntity<RestResponse> restResponseResponseEntity = sendPost(uri, accountDTO, RestResponse.class);
        return restResponseResponseEntity.getHeaders().getLocation().getPath();
    }
}
