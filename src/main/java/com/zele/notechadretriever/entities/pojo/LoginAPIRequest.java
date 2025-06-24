package com.zele.notechadretriever.entities.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAPIRequest {
    private String username;
    private String password;
}
