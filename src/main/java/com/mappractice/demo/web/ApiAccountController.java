package com.mappractice.demo.web;

import com.mappractice.demo.dto.AccountReturnDTO;
import com.mappractice.demo.dto.AccountSignUpDTO;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.response.ResponseGenerator;
import com.mappractice.demo.response.RestResponse;
import com.mappractice.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(UriResource.ACCOUNTS_V1_URI)
public class ApiAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<RestResponse<List<AccountReturnDTO>>> getList() {
        List<AccountReturnDTO> accountList = accountService.getList();
        return ResponseGenerator.generateListResponseEntity(accountList, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<RestResponse<AccountReturnDTO>> getAccount(@PathVariable Long accountId) {
        AccountReturnDTO accountReturnDTO = accountService.get(accountId);
        return ResponseGenerator.generateResponseEntity(
                accountReturnDTO
                , UriResource.ACCOUNTS_V1_URI
                , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RestResponse<AccountReturnDTO>> create(@RequestBody AccountSignUpDTO accountSignUpDTO) {
        AccountReturnDTO accountReturnDTO = accountService.create(accountSignUpDTO);
        return ResponseGenerator.generateCreatedResponseEntity(
                accountReturnDTO,
                accountReturnDTO.generateUrl(UriResource.ACCOUNTS_V1_URI));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<RestResponse<AccountReturnDTO>> update(@PathVariable Long accountId, @RequestBody AccountSignUpDTO accountSignUpDTO) {
        AccountReturnDTO accountReturnDTO = accountService.update(accountId, accountSignUpDTO);
        return ResponseGenerator.generateResponseEntity(
                accountReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<RestResponse<AccountReturnDTO>> delete(@PathVariable Long accountId) {
        AccountReturnDTO accountReturnDTO = accountService.delete(accountId);
        return ResponseGenerator.generateResponseEntity(
                accountReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponse<AccountReturnDTO>> login(HttpSession session, @RequestBody LoginDTO loginDTO){
        AccountReturnDTO accountReturnDTO =  accountService.login(session, loginDTO);
        return ResponseGenerator.generateResponseEntity(
                accountReturnDTO,
                UriResource.ACCOUNTS_V1_URI,
                HttpStatus.OK);
    }
}
