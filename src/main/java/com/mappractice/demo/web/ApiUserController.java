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

@CrossOrigin
@RestController
@RequestMapping
public class ApiUserController {

    @Autowired
    private UserService userService;

//    @GetMapping("")
//    public ResponseEntity<RestResponse<List<AccountReturnDTO>>> getList() {
//        List<AccountReturnDTO> accountList = userService.getList();
//        return ResponseGenerator.generateListResponseEntity(accountList, HttpStatus.OK);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> getUser(@PathVariable Long userId) {
        UserReturnDTO userReturnDTO = userService.get(userId);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO
                , UriResource.ACCOUNTS_V1_URI
                , HttpStatus.OK);
    }

//    @PostMapping("")
//    public ResponseEntity<RestResponse<AccountReturnDTO>> create(@RequestBody AccountSignUpDTO accountSignUpDTO) {
//        AccountReturnDTO accountReturnDTO = userService.create(accountSignUpDTO);
//        return ResponseGenerator.generateCreatedResponseEntity(
//                accountReturnDTO,
//                accountReturnDTO.generateUrl(UriResource.ACCOUNTS_V1_URI));
//    }

    @PutMapping("/{userId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> update(@PathVariable Long userId, @RequestBody UserSignUpDTO userSignUpDTO) {
        UserReturnDTO userReturnDTO = userService.update(userId, userSignUpDTO);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<RestResponse<UserReturnDTO>> delete(@PathVariable Long userId) {
        UserReturnDTO userReturnDTO = userService.delete(userId);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @PostMapping("/login/process")
    public ResponseEntity<RestResponse<UserReturnDTO>> login(HttpSession session, @RequestBody LoginDTO loginDTO){
        UserReturnDTO userReturnDTO =  userService.login(session, loginDTO);
        return ResponseGenerator.generateResponseEntity(
                userReturnDTO,
                HttpStatus.OK);
    }
}
