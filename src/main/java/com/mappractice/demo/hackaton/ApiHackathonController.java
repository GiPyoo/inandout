package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hackathonApi", produces = "text/plain;charset=UTF-8")
public class ApiHackathonController {

    private ApiHackathonService apiHackathonService;

    public ApiHackathonController(ApiHackathonService apiHackathonService) {
        this.apiHackathonService = apiHackathonService;
    }

    @GetMapping("/getAccountTransactionHistory")
    public TransactionHistoryResponseDTO getAccountHistory(
            @RequestParam(name="account", required = true) String account){
        return apiHackathonService.getAccountHistory(account);
    }

    @PostMapping("/saveAccountTransactionHistory")
    public TransactionHistory saveAccountHistory(
            @ModelAttribute TransactionHistory transactionHistory){
        System.out.println("getAccountHistory method : " + transactionHistory);
        return apiHackathonService.saveAccountHistory(transactionHistory);
    }
}
