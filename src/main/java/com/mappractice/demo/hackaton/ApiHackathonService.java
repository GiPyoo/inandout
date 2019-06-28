package com.mappractice.demo.hackaton;

import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ApiHackathonService {

    private TransactionHistoryRepository transactionHistoryRepository;

    public ApiHackathonService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public TransactionHistoryResponseDTO getAccountHistory() {
        TransactionHistoryResponseDTO transactionHistoryResponseDTO
                = new TransactionHistoryResponseDTO();

        transactionHistoryResponseDTO.setAccount(new Account(0l, "0019", "-41306", "04821098763", "19580694",""));
        transactionHistoryResponseDTO.getDatas()
                .add(new TransactionHistory(0l,"", "20180102","9581237","종암동","111","0","","111","0"));

        transactionHistoryResponseDTO.getDatas().add(new TransactionHistory(0l,"월급","20180403","9581237","종암동","111","111","","0","0"));
        return transactionHistoryResponseDTO;
    }

    public TransactionHistory saveAccountHistory(TransactionHistory transactionHistory) {
        return transactionHistoryRepository.save(transactionHistory);
    }

}
