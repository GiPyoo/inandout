package com.mappractice.demo.dto;

import com.mappractice.demo.domain.Account;
import com.mappractice.demo.exception.UnAuthorizedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSignUpDTO {

    private String email;

    private String password;

    private String passwordCheck;

    private String name;

    public boolean passwordConfirm() {
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

    public Account toEntity() {
        if (!passwordConfirm()) {
            throw new UnAuthorizedException("unmatch password");
        }
        return new Account(email, password, name);
    }

}
