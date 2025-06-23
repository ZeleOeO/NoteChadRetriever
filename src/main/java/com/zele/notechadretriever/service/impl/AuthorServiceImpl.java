package com.zele.notechadretriever.service.impl;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.AuthorAPIResponse;
import com.zele.notechadretriever.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final RestTemplate restTemplate;

    public AuthorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<DefaultAPIResponse<List<AuthorAPIResponse.AuthorWrapper>>> getAuthor(String token) {
        String BASE_URL = "http://localhost:8080/api/authors/all";
        try {
            HttpHeaders headers = new HttpHeaders();
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultAPIResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null));
        } catch (HttpServerErrorException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DefaultAPIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
        } catch (ResourceAccessException ex) {
            // handle network errors or timeouts
        } catch (RestClientException ex) {
            // handle any other errors
        }
        return null;
    }
}
