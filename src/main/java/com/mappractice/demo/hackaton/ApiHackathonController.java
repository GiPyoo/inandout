package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hackathonApi")
public class ApiHackathonController {

    private ApiHackathonService apiHackathonService;

    public ApiHackathonController(ApiHackathonService apiHackathonService) {
        this.apiHackathonService = apiHackathonService;
    }

    @GetMapping("/getAccountTransactionHistory")
    public TransactionHistoryResponseDTO getAccountHistory(){
        return apiHackathonService.getAccountHistory();
    }

    @PostMapping("/saveAccountTransactionHistory")
    public TransactionHistory saveAccountHistory(
            @ModelAttribute TransactionHistory transactionHistory){
        System.out.println("getAccountHistory method : " + transactionHistory);
        return apiHackathonService.saveAccountHistory(transactionHistory);
    }
}
