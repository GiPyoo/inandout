package com.mappractice.demo.domain;

import com.mappractice.demo.dto.AccountSignUpDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public boolean matchPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public Account update(AccountSignUpDTO accountSignUpDTO) {
        if (!this.getPassword().equals(password)) {
            throw new UnAuthorizedException("unvalid password");
        }
        email = accountSignUpDTO.getEmail();
        name = accountSignUpDTO.getName();
        return this;
    }
}
