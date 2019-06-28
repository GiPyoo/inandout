package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ApiHackathonService {

    public TransactionHistoryResponseDTO getAccountHistory() {
        TransactionHistoryResponseDTO transactionHistoryResponseDTO
                = new TransactionHistoryResponseDTO();

        transactionHistoryResponseDTO.setAccount(new Account(0l, "0019", "-41306", "04821098763", "19580694",""));
        transactionHistoryResponseDTO.getDatas().add(new TransactionHistory(0l,"20170316","9581237","종암동","111","0","","111","0"));
        transactionHistoryResponseDTO.getDatas().add(new TransactionHistory(0l,"20170316","9581237","종암동","111","0","","111","0"));
        return transactionHistoryResponseDTO;
    }
}
