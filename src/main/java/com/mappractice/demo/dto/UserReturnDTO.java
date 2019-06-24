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

    private String email;

    private String name;

    public UserReturnDTO(User user) {
        this.id = user.getId();
        this.email = user.getAccount();
        this.name = user.getName();
    }

    @Override
    public String generateUrl(String path) {
        return path+"/"+id;
    }
}
