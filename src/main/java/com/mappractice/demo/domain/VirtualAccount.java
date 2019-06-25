package com.mappractice.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VirtualAccount {

    @Id
    @Column(nullable = false)
    private String account;

    @Id
    @OneToOne
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long amount;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<AccountHistory> histories;
}
