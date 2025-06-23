package com.zele.notechadretriever.controllers;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.LoginAPIRequest;
import com.zele.notechadretriever.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<DefaultAPIResponse<String>> login(@RequestBody LoginAPIRequest loginAPIRequest) {
        return authService.login(loginAPIRequest);
    }
}
