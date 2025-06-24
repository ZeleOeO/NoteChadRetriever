package com.zele.notechadretriever.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zele.notechadretriever.entities.pojo.LoginAPIRequest;
import com.zele.notechadretriever.service.AuthService;
import com.zele.notechadretriever.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final AuthService authService;
    @Value("${credentials.username}")
    String username;
    @Value("${credentials.password}")
    String password;

    public JwtServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    public Map<String, Object> decodeJwt(String jwtToken) {
        try {
            String[] parts = jwtToken.split("\\.");
            if (parts.length != 3) {
                log.error("Invalid JWT Token: {}", jwtToken);
                return new HashMap<>();
            }

            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payload, Map.class);
        } catch (Exception e) {
            log.error("JWT token could not be decoded.");
            return new HashMap<>();
        }
    }

    public boolean validateExpiry(String jwtToken) {
        try {
            var claims = decodeJwt(jwtToken);
            if (claims.isEmpty()) {
                log.error("Invalid Expiration: {}", jwtToken);
                throw new RuntimeException("Invalid Expiration: " + jwtToken);
            }
            long exp = ((Integer) claims.get("exp")).longValue();

            Date expDate = new Date(exp * 1000);
            if (expDate.before(new Date())) {
                log.warn("Token Expired: {}", jwtToken);
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String refreshToken(String jwtToken) {
        try {
            if (!validateExpiry(jwtToken)) {
                return authService.login(new LoginAPIRequest(username, password)).getBody().getData();
            } else {
                return jwtToken;
            }
        } catch (Exception e) {
            log.error("Refresh Token Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
