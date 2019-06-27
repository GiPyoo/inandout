package com.mappractice.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccount {

    @Id
    @ManyToOne
    @Column(nullable = false)
    private User user;

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
