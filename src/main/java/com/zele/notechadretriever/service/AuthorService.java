package com.zele.notechadretriever.service;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.AuthorAPIResponse;
import com.zele.notechadretriever.entities.pojo.LoginAPIRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    ResponseEntity<DefaultAPIResponse<List<AuthorAPIResponse.AuthorWrapper>>> getAuthor(String token);
}
