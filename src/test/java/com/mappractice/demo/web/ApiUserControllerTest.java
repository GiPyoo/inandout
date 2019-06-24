package com.mappractice.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mappractice.demo.dto.UserReturnDTO;
import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.exception.RestReponseEntityExceptionHandler;
import com.mappractice.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.mappractice.demo.acceptanceTest.AcceptanceUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ApiUserControllerTest {
    private final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);
    private final String API_ACCOUNT_URI = UriResource.ACCOUNTS_V1_URI;

    @Mock
    private UserService userService;

    @InjectMocks
    private ApiUserController apiUserController;

    private MockMvc mockMvc;
    private JacksonTester<UserSignUpDTO> jsonAccountSignUpDTO;
    private JacksonTester<LoginDTO> jsonLoginDTO;

    @Before
    public void setup() {
        JacksonTester.initFields(this, ObjectMapper::new);

        mockMvc = MockMvcBuilders.standaloneSetup(apiUserController)
                .setControllerAdvice(new RestReponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void create_성공() throws Exception {
        //given
        when(userService.create(any(UserSignUpDTO.class))).thenReturn(userReturnDTO);
        //when
        MockHttpServletResponse response =
                mockMvc.perform(post(API_ACCOUNT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAccountSignUpDTO.write(userSignUpDTO).getJson()))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void delete_성공() throws Exception {
        //given
        when(userService.delete(isA(Long.class))).thenReturn(userReturnDTO);

        //when
        MockHttpServletResponse response =
                mockMvc.perform(delete(API_ACCOUNT_URI + "/{id}", 1))
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void update_성공() throws Exception {
        //given
        when(userService.update(isA(Long.class), any(UserSignUpDTO.class))).thenReturn(userReturnDTO);

        //when
        MockHttpServletResponse response =
                mockMvc.perform(put(API_ACCOUNT_URI + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAccountSignUpDTO.write(userSignUpDTO).getJson()))
                        .andExpect(status().isOk())
                        .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void get_list_성공() throws Exception {
        //given
        List<UserReturnDTO> listForReturn = new ArrayList();
        listForReturn.add(userReturnDTO);
        when(userService.getList()).thenReturn(listForReturn);
        //when
        MockHttpServletResponse response = mockMvc.perform(get(API_ACCOUNT_URI))
                .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void get_account_성공() throws Exception {
        //given
        when(userService.get(isA(Long.class))).thenReturn(userReturnDTO);
        //when
        MockHttpServletResponse response = mockMvc.perform(get(API_ACCOUNT_URI + "/{id}", 1))
                .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void post_login_성공() throws Exception {
        //when
        MockHttpServletResponse response = mockMvc.perform(post(API_ACCOUNT_URI+"/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonLoginDTO.write(loginDTO).getJson()))
                .andReturn().getResponse();
        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}