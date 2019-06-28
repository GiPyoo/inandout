package com.mappractice.demo.hackaton.dto;

import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionHistoryResponseDTO {

    private Account account;

    private List<TransactionHistory> datas;
}
