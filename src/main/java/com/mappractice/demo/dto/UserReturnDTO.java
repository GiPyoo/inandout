package com.mappractice.demo.dto;

import com.mappractice.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserReturnDTO implements UrlGeneratable{

    private Long id;

    private String account;

    private String name;

    public UserReturnDTO(User account) {
        this.id = account.getId();
        this.account = account.getAccount();
        this.name = account.getName();
    }

    @Override
    public String generateUrl(String path) {
        return path+"/"+id;
    }
}
