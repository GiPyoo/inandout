package com.mappractice.demo.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ParserUtils {
    private static final String PARSING_WORD = "datas";

    public static void parseStringToJson(String input) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject) parser.parse(input);
        JsonArray memberArray = (JsonArray) jsonObj.get(PARSING_WORD);

        for (int i = 0; i < memberArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) memberArray.get(i);
            System.out.println(jsonObject.get("transactionDate"));
            System.out.println(jsonObject.get("amount"));
            System.out.println(jsonObject.get("cash"));
            System.out.println(jsonObject.get("client"));
            System.out.println(jsonObject.get("id"));
            System.out.println(jsonObject.get("inputCash"));
            System.out.println(jsonObject.get("originalCash"));
            System.out.println(jsonObject.get("outputCash"));
            System.out.println(jsonObject.get("place"));
            System.out.println(jsonObject.get("transactionType"));
        }
    }
}
