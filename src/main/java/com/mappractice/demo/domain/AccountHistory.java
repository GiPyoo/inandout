package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AccountHistory {

    @Id
    private String account;

    @OneToOne
    @Column
    private Category category;

    @Column
    private LocalDateTime date;

    @Column
    private String client;

    @Column
    private int transaction;

    @Column
    private Long money;

    @Column
    private Long amount;
}
