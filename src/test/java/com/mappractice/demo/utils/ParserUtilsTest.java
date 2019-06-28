package com.mappractice.demo.utils;

import com.mappractice.demo.web.UriResource;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParserUtilsTest {
    @Test
    public void get() throws Exception {
        String str = RequestGenerator.getJson("http://localhost:8080" + UriResource.ACCOUNTS_V1_URI);

        ParserUtils.parseStringToJson(str);
    }
}
