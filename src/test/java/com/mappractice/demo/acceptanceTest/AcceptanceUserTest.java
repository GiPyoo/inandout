package com.mappractice.demo.acceptanceTest;

import com.mappractice.demo.domain.User;
import com.mappractice.demo.domain.UserRepository;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.dto.UserReturnDTO;
import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.response.RestResponse;
import com.mappractice.demo.support.AcceptanceTest;
import com.mappractice.demo.web.ApiUserController;
import com.mappractice.demo.web.UriResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceUserTest extends AcceptanceTest {
    private final Logger log = LoggerFactory.getLogger(AcceptanceUserTest.class);
    private final String ACCOUNT_URI = UriResource.ACCOUNTS_V1_URI;

    @Autowired
    private ApiUserController apiUserController;

    @Autowired
    private UserRepository userRepository;

    public static UserSignUpDTO userSignUpDTO;
    public static User user;
    public static UserReturnDTO userReturnDTO;
    public static LoginDTO loginDTO;

    static {
        userSignUpDTO = UserSignUpDTO.builder()
                .account("test@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        user = new User(2l, "test@naver.com", "!Test1234", "testName");
        userReturnDTO = new UserReturnDTO(2l, "test@naver.com", "testName");
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
        UserSignUpDTO accountDTO = UserSignUpDTO.builder()
                .account("test3@naver.com")
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
        UserSignUpDTO userDTO = UserSignUpDTO.builder()
                .account("test4@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        String resourceURI = createResource(ACCOUNT_URI, userDTO);

        LoginDTO loginDTO3 = new LoginDTO("test4@naver.com", "!Test1234");

        ResponseEntity<RestResponse> responseEntity = sendPost(ACCOUNT_URI+"/login" ,loginDTO3, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void create_성공() {
        UserSignUpDTO userDTO = UserSignUpDTO.builder()
                .account("test1@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        ResponseEntity<RestResponse> responseEntity = sendPost(ACCOUNT_URI, userDTO, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(userRepository.findByName(userDTO.getName()).isPresent()).isEqualTo(true);
    }

    @Test
    public void update_성공() {
        UserSignUpDTO userDTO = UserSignUpDTO.builder()
                .account("test2@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        String resourceURI = createResource(ACCOUNT_URI, userDTO);

        UserSignUpDTO changedUserDTO = UserSignUpDTO.builder()
                .account("test3@naver.com")
                .name("changedName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();

        ResponseEntity<RestResponse> responseEntity = sendPut(resourceURI, changedUserDTO, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findByName(changedUserDTO.getName()).get().getName()).isEqualTo("changedName");
    }

    @Test
    public void delete_성공() {
        UserSignUpDTO userDTO = UserSignUpDTO.builder()
                .account("test2@naver.com")
                .name("testName")
                .password("!Test1234")
                .passwordCheck("!Test1234")
                .build();
        String resourceURI = createResource(ACCOUNT_URI, userDTO);
        ResponseEntity<RestResponse> responseEntity = sendDelete(resourceURI, RestResponse.class);

        log.info("body : {}", responseEntity.getBody());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findByName(userDTO.getName()).isPresent()).isEqualTo(false);
    }

    protected String createResource(String uri, UserSignUpDTO accountDTO) {
        ResponseEntity<RestResponse> restResponseResponseEntity = sendPost(uri, accountDTO, RestResponse.class);
        return restResponseResponseEntity.getHeaders().getLocation().getPath();
    }
}
