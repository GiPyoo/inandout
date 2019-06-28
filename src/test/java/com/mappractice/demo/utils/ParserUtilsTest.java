package com.mappractice.demo.utils;

import org.junit.Test;

public class ParserUtilsTest {
    @Test
    public void get() {
        String str = "{" +
                "\"account\": {" +
                "\"amount\": \"string\"," +
                "\"amountNumber\": \"string\"," +
                "\"gridInfo\": \"string\"," +
                "\"id\": 0," +
                "\"nextTransactionDate\": \"string\"," +
                "\"withdrawableAmount\": \"string\"" +
                "}," +
                "\"datas\": [" +
                "{" +
                "\"amount\": \"string\"," +
                "\"cash\": \"string\"," +
                "\"client\": \"string\"," +
                "\"id\": 0," +
                "\"inputCash\": \"string\"," +
                "\"originalCash\": \"string\"," +
                "\"outputCash\": \"string\"," +
                "\"place\": \"string\"," +
                "\"transactionType\": \"string\"" +
                "}]}";

        ParserUtils.parseStringToJson(str);
    }
}
