package com.mappractice.demo.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mappractice.demo.hackaton.domain.Account;
import com.mappractice.demo.hackaton.domain.TransactionHistory;


public class ParserUtils {
    private static final String DATAS = "datas"; /* json형식 정해지면 수정*/
    private static final String ACCOUNT = "account";

    public static void parseStringToJson(String input) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject) parser.parse(input);

        JsonArray datasArray = (JsonArray) jsonObj.get(DATAS);
        JsonArray accountArray = (JsonArray) jsonObj.get(ACCOUNT);

        if (!datasArray.isJsonNull()) {
            sendDatasJson(datasArray);
        }

        if (!accountArray.isJsonNull()) {
            sendAccountJson(accountArray);
        }
    }

    private static void sendAccountJson(JsonArray accountArray) {
        for (int i = 0; i < accountArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) accountArray.get(i);
            Account account = new Account();
            account.setAmount(jsonObject.get("amount").toString());
            account.setAmountNumber(jsonObject.get("amountNumber").toString());
            account.setGridInfo(jsonObject.get("gridInfo").toString());
            account.setId(Long.parseLong(jsonObject.get("id").toString()));
            account.setNextTransactionDate(jsonObject.get("nextTransactionDate").toString());
            account.setWithdrawableAmount(jsonObject.get("withdrawableAmount").toString());
        }
    }

    private static void sendDatasJson(JsonArray datasArray) {
        for (int i = 0; i < datasArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) datasArray.get(i);
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setAmount(jsonObject.get("amount").toString());
            transactionHistory.setCash(jsonObject.get("cash").toString());
            transactionHistory.setClient(jsonObject.get("client").toString());
            transactionHistory.setId(Long.parseLong(jsonObject.get("id").toString()));
            transactionHistory.setInputCash(jsonObject.get("inputCash").toString());
            transactionHistory.setOriginalCash(jsonObject.get("originalCash").toString());
            transactionHistory.setOutputCash(jsonObject.get("outputCash").toString());
            transactionHistory.setPlace(jsonObject.get("place").toString());
            transactionHistory.setTransactionType(jsonObject.get("transactionType").toString());
        }
    }
}
