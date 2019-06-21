package com.mappractice.demo.dto;

import com.mappractice.demo.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountReturnDTO implements UrlGeneratable{

    private Long id;

    private String email;

    private String name;

    public AccountReturnDTO(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
    }

    @Override
    public String generateUrl(String path) {
        return path+"/"+id;
    }
}
