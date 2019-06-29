package com.mappractice.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualAccountCreateDTO {

    private String name;
    private Long amount;
    private Long categoryId;
}
