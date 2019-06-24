package com.mappractice.demo.web;

import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.dto.UserReturnDTO;
import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.response.ResponseGenerator;
import com.mappractice.demo.response.RestResponse;
import com.mappractice.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class ApiUserController {

    @Autowired
    private UserService userService;

//    @GetMapping("")
//    public ResponseEntity<RestResponse<List<UserReturnDTO>>> getList() {
//        List<UserReturnDTO> userList = userService.getList();
//        return ResponseGenerator.generateListResponseEntity(userList, HttpStatus.OK);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> getUser(@PathVariable Long userId) {
        UserReturnDTO userReturnDTO = userService.get(userId);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO
                , UriResource.ACCOUNTS_V1_URI
                , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RestResponse<UserReturnDTO>> create(@RequestBody UserSignUpDTO userSignUpDTO) {
        UserReturnDTO userReturnDTO = userService.create(userSignUpDTO);
        return ResponseGenerator.generateCreatedResponseEntity(
                userReturnDTO,
                userReturnDTO.generateUrl(UriResource.ACCOUNTS_V1_URI));
    }

    @PutMapping("/{UserId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> update(@PathVariable Long UserId, @RequestBody UserSignUpDTO userSignUpDTO) {
        UserReturnDTO userReturnDTO = userService.update(UserId, userSignUpDTO);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @DeleteMapping("/{UserId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> delete(@PathVariable Long UserId) {
        UserReturnDTO userReturnDTO = userService.delete(UserId);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @PutMapping("/login/process")
    public ResponseEntity<RestResponse<UserReturnDTO>> login(HttpSession session, @RequestBody LoginDTO loginDTO) {
        UserReturnDTO userReturnDTO = userService.login(session, loginDTO);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                HttpStatus.OK);
    }
}
