package com.mappractice.demo.hackaton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDTO {

    private Map<String, String> dataHeader = new HashMap<>();

    private Map<String, String> dataBody = new HashMap<>();
}
