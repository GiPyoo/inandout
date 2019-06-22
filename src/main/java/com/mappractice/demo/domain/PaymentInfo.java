package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String aid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tid;

    @Column(nullable = false)
    private String cid;

    @Column(nullable = false)
    private String paymentMethodType;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private CardInfo cardInfo;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private DateTime createdAt;

    @Column(nullable = false)
    private DateTime approvedAt;

}
