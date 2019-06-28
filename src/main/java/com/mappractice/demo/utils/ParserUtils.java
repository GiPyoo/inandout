package com.mappractice.demo.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ParserUtils {
    private static final String PARSING_WORD = "dates";

    public static void parseStringToJson(String input) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject) parser.parse(input);
        JsonArray memberArray = (JsonArray) jsonObj.get(PARSING_WORD);

        for (int i = 0; i < memberArray.size(); i++) {
            System.out.println(memberArray.get(i));
        }
    }
}
