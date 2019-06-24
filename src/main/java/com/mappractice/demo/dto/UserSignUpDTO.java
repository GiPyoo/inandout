package com.mappractice.demo.dto;

import com.mappractice.demo.domain.User;
import com.mappractice.demo.exception.UnAuthorizedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDTO {

    private String account;

    private String password;

    private String passwordCheck;

    private String name;

    public boolean passwordConfirm() {
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

    public User toEntity() {
        if (!passwordConfirm()) {
            throw new UnAuthorizedException("unmatch password");
        }
        return new User(account, password, name);
    }

}
