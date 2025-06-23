package com.zele.notechadretriever.entities.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginAPIRequest {
    private String username;
    private String password;
}
