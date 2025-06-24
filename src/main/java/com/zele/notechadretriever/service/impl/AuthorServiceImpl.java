package com.zele.notechadretriever.service.impl;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.AuthorAPIResponse;
import com.zele.notechadretriever.service.AuthorService;
import com.zele.notechadretriever.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    public AuthorServiceImpl(RestTemplate restTemplate, JwtService jwtService) {
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<DefaultAPIResponse<List<AuthorAPIResponse.AuthorWrapper>>> getAuthor(String token) {
        String BASE_URL = "http://localhost:8080/api/authors/all";
        try {
            HttpHeaders headers = new HttpHeaders();
            token = jwtService.refreshToken(token);
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            AuthorAPIResponse apiResponse = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.GET,
                    entity,
                    AuthorAPIResponse.class
            ).getBody();

            return ResponseEntity.status(HttpStatus.OK).body(new DefaultAPIResponse<>(apiResponse.getStatus(), apiResponse.getMessage(), apiResponse.getData()));
        } catch (HttpClientErrorException ex) {
            log.error("Http Client Error: ", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultAPIResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null));
        } catch (HttpServerErrorException ex) {
            log.error("Http Server Error: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DefaultAPIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
        }
    }
}
