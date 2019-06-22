package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CardInfo {

    @Column(nullable = false)
    private String purchaseCorp;

    @Column(nullable = false)
    private String purchaseCorpCode;

    @Column(nullable = false)
    private String issuerCorp;

    @Column(nullable = false)
    private String issuerCorpCode;

    @Column(nullable = false)
    private String bin;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private String installMonth;

    @Column(nullable = false)
    private String approvedId;
}
