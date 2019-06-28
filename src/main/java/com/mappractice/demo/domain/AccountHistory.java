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
    public static final int DEPOSIT_CODE = 0;
    public static final int WITHDRAW_CODE = 1;

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

    public void createDeposit(String account, VirtualAccount virtualAccount, Long money) {
        this.account = account;
        this.virtualAccount = virtualAccount;
        this.createdAt = LocalDateTime.now();
        this.transaction = 0;
        this.deposit = money;
        this.withdraw = 0l;
        this.amount = virtualAccount.getAmount() + money;
    }

    public void createWithDraw(String account, VirtualAccount virtualAccount, Long money) {
        this.account = account;
        this.virtualAccount = virtualAccount;
        this.createdAt = LocalDateTime.now();
        this.transaction = 1;
        this.deposit = 0l;
        this.withdraw = money;
        this.amount = virtualAccount.getAmount() - money;
    }
}
