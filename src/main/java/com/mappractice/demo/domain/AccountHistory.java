package com.mappractice.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String account;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_history_to_virtual"))
    private VirtualAccount virtualAccount;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private int transaction;

    @Column
    private Long deposit;

    @Column
    private Long withdraw;

    @Column
    private Long amount;

}
