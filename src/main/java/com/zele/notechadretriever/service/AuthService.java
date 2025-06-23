package com.zele.notechadretriever.service;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.LoginAPIRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<DefaultAPIResponse<String>> login(LoginAPIRequest loginAPIRequest);
}
