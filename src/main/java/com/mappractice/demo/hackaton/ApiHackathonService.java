package com.mappractice.demo.hackaton;

import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.hackaton.domain.ApiAccount;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.domain.TransactionHistoryRepository;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional
    public TransactionHistory saveAccountHistory(TransactionHistory transactionHistory) {
        if(transactionHistory.getTransactionDate()==null){
         transactionHistory.setTransactionDate(LocalDateTime.now());
        }
        ApiAccount apiAccount = apiAccountRepository.findById(1l).orElseThrow(UnAuthorizedException::new);
        int amount = Integer.parseInt(apiAccount.getAmount());
        int inputCash = Integer.parseInt(transactionHistory.getInputCash());
        int outputCash = Integer.parseInt(transactionHistory.getOutputCash());
        amount = amount + inputCash - outputCash;
        if(amount<0){
            throw new RuntimeException("잔액이 부족합니다");
        }
        //account의 amount 설정
        apiAccount.setAmount(String.valueOf(amount));
        apiAccountRepository.save(apiAccount);

        //amount계산해서 등록
        transactionHistory.setAmount(String.valueOf(amount));
        return transactionHistoryRepository.save(transactionHistory);
    }

}
