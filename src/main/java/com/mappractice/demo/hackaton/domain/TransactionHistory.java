package com.mappractice.demo.hackaton.domain;

import com.mappractice.demo.support.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory extends AbstractEntity {

    // "의뢰인":"", "거래일시":"20170316", "잔액":"9581237", "취급점":"종암동",
    // "거래금액":"111", "입금금액":"0", "통장적요":"", "지급금액":"111", "거래원금":"0"

    @Id
    @GeneratedValue
    private Long id;

    private String client;

    private String amount;

    private String place;

    private String cash;

    private String inputCash;

    private String TransactionType;

    private String outputCash;

    private String originalCash;


}
