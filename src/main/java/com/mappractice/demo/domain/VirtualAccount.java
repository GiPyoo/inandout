package com.mappractice.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_virtual_category"))
    private Category category;

    @Column(nullable = false)
    private Long amount;

    public boolean isSameId(Long accountId) {
        return category.getId() == accountId;
    }

    public void updateAmount(Long amount) {
        this.amount = amount;
    }

    public void reflectHistory(AccountHistory accountHistory) {
        if (accountHistory.getTransaction() == 0) {
            this.amount += accountHistory.getDeposit();
        }
        if (accountHistory.getTransaction() == 1) {
            this.amount -= accountHistory.getWithdraw();
        }
    }
    public void depositMoney(Long change){
        amount= amount +change;
    }

}
