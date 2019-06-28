package com.mappractice.demo.web;

import com.mappractice.demo.domain.VirtualAccount;
import com.mappractice.demo.response.ResponseGenerator;
import com.mappractice.demo.response.RestResponse;
import com.mappractice.demo.service.VirtualAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(UriResource.ACCOUNTS_V1_URI + "/virtualAccounts")
public class ApiVirtualAccountController {

    @Autowired
    private VirtualAccountService virtualAccountService;

    @GetMapping("/")
    public ResponseEntity<RestResponse<List<VirtualAccount>>> getList(HttpSession session) {
        return ResponseGenerator.generateResponseEntity(
                virtualAccountService.getList(session),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createAccount(HttpSession session, Long categoryId) {
        virtualAccountService.create(session, categoryId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<RestResponse<VirtualAccount>> updateAccount(HttpSession session, @PathVariable Long accountId) {
        virtualAccountService.update(session, accountId);

        return ResponseGenerator.generateResponseEntity(
                virtualAccountService.getAccount(accountId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity deleteAccount(HttpSession session, @PathVariable Long accountId) {
        virtualAccountService.delete(session, accountId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<RestResponse<VirtualAccount>> getAccount(@PathVariable Long accountId) {
        return ResponseGenerator.generateResponseEntity(
                virtualAccountService.getAccount(accountId),
                HttpStatus.OK);
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<RestResponse<VirtualAccount>> deposit(HttpSession session, @PathVariable Long accountId, Long money) {
        virtualAccountService.deposit(session, accountId, money);

        return ResponseGenerator.generateResponseEntity(
                virtualAccountService.getAccount(accountId),
                HttpStatus.OK);
    }

}