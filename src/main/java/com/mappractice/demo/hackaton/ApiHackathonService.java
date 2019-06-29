package com.mappractice.demo.hackaton;

import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.hackaton.domain.ApiAccount;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.domain.TransactionHistoryRepository;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ApiHackathonService {

    private ApiAccountRepository apiAccountRepository;
    private TransactionHistoryRepository transactionHistoryRepository;

    public ApiHackathonService(ApiAccountRepository apiAccountRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.apiAccountRepository = apiAccountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public TransactionHistoryResponseDTO getAccountHistory() {
        TransactionHistoryResponseDTO transactionHistoryResponseDTO
                = new TransactionHistoryResponseDTO();

        ApiAccount apiAccount = apiAccountRepository.findById(1L).orElseThrow(UnAuthorizedException::new);
        transactionHistoryResponseDTO.setAccount(apiAccount);
        transactionHistoryResponseDTO.setDatas(transactionHistoryRepository.findAll());

        return transactionHistoryResponseDTO;
    }

    public TransactionHistory saveAccountHistory(TransactionHistory transactionHistory) {
        return transactionHistoryRepository.save(transactionHistory);
    }

}
