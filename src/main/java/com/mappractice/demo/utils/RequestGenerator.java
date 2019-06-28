package com.mappractice.demo.utils;

import com.mappractice.demo.hackaton.dto.TransactionHistoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class RequestGenerator {
//    public static String getJson(String url) throws Exception {
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Accept", "application/json");
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("Response Code : " + responseCode);
//
//        return response.toString();
//    }

    public static TransactionHistoryResponseDTO getJsonByRestTemplate(String url) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .build().toUri();

        ResponseEntity<TransactionHistoryResponseDTO> response = restTemplate.getForEntity(uri, TransactionHistoryResponseDTO.class);
        return response.getBody();

//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        System.out.println(response.toString());
//        return response.toString();


    }
}
