package com.mappractice.demo.web;

import com.mappractice.demo.dto.TransactionHistoryDTO;
import com.mappractice.demo.service.ApiHackathonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hackathon", produces = "text/plain;charset=UTF-8")
public class ApiHackathonController {

    private ApiHackathonService apiHackathonService;

    public ApiHackathonController(ApiHackathonService apiHackathonService) {
        this.apiHackathonService = apiHackathonService;
    }

    @PostMapping("/getAccountTransactionHistory")
    public TransactionHistoryDTO getAccountHistory(){

        return apiHackathonService.getAccountHistory();
    }
}
