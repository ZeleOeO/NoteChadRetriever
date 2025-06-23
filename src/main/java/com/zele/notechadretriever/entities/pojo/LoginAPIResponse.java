package com.zele.notechadretriever.entities.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginAPIResponse {
    private int status;
    private String message;
    @JsonProperty("data")
    private String jwt ;
}
