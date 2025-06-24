package com.zele.notechadretriever.service;

import java.util.Map;

public interface JwtService {
    Map<String, Object> decodeJwt(String jwt);
    boolean validateExpiry(String jwt);
    String refreshToken(String jwt);
}
