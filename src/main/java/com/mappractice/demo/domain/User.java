package com.mappractice.demo.domain;

import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<VirtualAccount> virtualAccounts;

    public User(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public boolean matchPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public User update(UserSignUpDTO userSignUpDTO) {
        if (!this.getPassword().equals(password)) {
            throw new UnAuthorizedException("unvalid password");
        }
        account = userSignUpDTO.getAccount();
        name = userSignUpDTO.getName();
        return this;
    }
}
