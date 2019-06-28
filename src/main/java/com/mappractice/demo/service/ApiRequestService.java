package com.mappractice.demo.service;

import com.mappractice.demo.hackaton.domain.TransactionHistory;
import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApiRequestService {

    public void test(TransactionHistoryResponseDTO transactionHistoryResponseDTO){
        List<TransactionHistory> datas = transactionHistoryResponseDTO.getDatas();
        Collections.sort(datas);
        int latestHistoryIndex =checkTime(datas);

        if(latestHistoryIndex==-1)
            return;

        for (int i=latestHistoryIndex; i<datas.size(); i++){
            //DB 입력....
        }
    }

    private int checkTime(List<TransactionHistory> histories){

        for (int i=0; i<histories.size(); i++){
            if(true){ //최신 정보 확인
                return i;
            }
        }

        return -1;
    }
}
