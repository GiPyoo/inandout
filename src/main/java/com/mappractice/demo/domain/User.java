package com.mappractice.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<VirtualAccount> virtualAccounts;

    @Column(nullable = false)
    private LocalDateTime userLatestTime = LocalDateTime.now();

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

    public void updateLatestTime(String latestDate) {
        this.userLatestTime = LocalDateTime.parse(latestDate);
    }
}
