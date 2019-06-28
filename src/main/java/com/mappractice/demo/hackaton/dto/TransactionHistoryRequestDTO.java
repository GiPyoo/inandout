package com.mappractice.demo.hackaton.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionHistoryRequestDTO {

    private String account;

    private String contractNumber;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate from;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate to;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate nextFrom;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate nextTo;
}
