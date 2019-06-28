package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.dto.TransactionHistoryRequestDTO;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public TransactionHistoryResponseDTO getAccountHistory(
            @ModelAttribute(value = "dataBody") TransactionHistoryRequestDTO requestDTO){
        System.out.println("getAccountHistory method : " + requestDTO);
        return apiHackathonService.getAccountHistory();
    }
}
