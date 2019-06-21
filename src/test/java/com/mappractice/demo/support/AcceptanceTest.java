package com.mappractice.demo.support;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return this.template;
    }

    protected String createResource(String uri, MultiValueMap bodyPayLoad) {
        return Objects.requireNonNull(sendFile(uri, bodyPayLoad, String.class)
                .getHeaders()
                .getLocation())
                .getPath();
    }

    protected <T> ResponseEntity<T> sendPost(String uri, Object bodyPayLoad, Class<T> responseType) {
        return template().postForEntity(uri, bodyPayLoad, responseType);
    }

    protected <T> ResponseEntity<T> sendGet(String uri, Class<T> responseType) {
        return template().getForEntity(uri, responseType);
    }

    protected <T> ResponseEntity<T> sendDelete(String uri, Class<T> responseType) {
        return template().exchange(uri, HttpMethod.DELETE, createHttpEntity(null), responseType);
    }

    protected <T> ResponseEntity<T> sendPut(String uri, Object bodyPayLoad, Class<T> responseType) {
        return template().exchange(uri, HttpMethod.PUT, createHttpEntity(bodyPayLoad), responseType);
    }

    protected <T> ResponseEntity<T> sendFile(String uri, MultiValueMap body, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
        return template().exchange(uri, HttpMethod.POST, entity, responseType, "");
    }

    private HttpEntity<Object> createHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
