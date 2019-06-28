package com.mappractice.demo.utils;

import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import com.mappractice.demo.service.ApiRequestService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParserUtilsTest {
    @Test
    public void get() throws Exception {
        TransactionHistoryResponseDTO jsonByRestTemplate = RequestGenerator.getJsonByRestTemplate("http://localhost:8080" + "/hackathonApi/getAccountTransactionHistory");

        //        ParserUtils.parseStringToJson(str);
    }

    @Test
    public void get2() throws Exception {
        ApiRequestService apiRequestService = new ApiRequestService();
        TransactionHistoryResponseDTO jsonByRestTemplate = RequestGenerator.getJsonByRestTemplate("http://localhost:8080" + "/hackathonApi/getAccountTransactionHistory");

        apiRequestService.test(jsonByRestTemplate);
        //        ParserUtils.parseStringToJson(str);
    }
}
