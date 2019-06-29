package com.mappractice.demo.hackaton.dto;

import com.mappractice.demo.hackaton.domain.ApiAccount;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionHistoryResponseDTO {

    private ApiAccount account;

    private List<TransactionHistory> datas = new ArrayList<>();
}
