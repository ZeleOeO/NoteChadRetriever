package com.zele.notechadretriever.service.impl;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.LoginAPIRequest;
import com.zele.notechadretriever.entities.pojo.LoginAPIResponse;
import com.zele.notechadretriever.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;

    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<DefaultAPIResponse<String>> login(LoginAPIRequest loginAPIRequest) {
        String BASE_URL = "http://localhost:8080/api/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginAPIRequest> entity = new HttpEntity<>(loginAPIRequest, headers);
        try {
            LoginAPIResponse loginAPIResponse = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.POST,
                    entity,
                    LoginAPIResponse.class
            ).getBody();

            if (loginAPIResponse == null) {return ResponseEntity.badRequest().build();}

            return ResponseEntity.status(HttpStatus.OK).body(new DefaultAPIResponse<>(loginAPIResponse.getStatus(), loginAPIResponse.getMessage(), loginAPIResponse.getJwt()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
