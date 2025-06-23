package com.zele.notechadretriever.entities.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorAPIResponse {
    private int status;
    private String message;
    private List<AuthorWrapper> data;

    @Data
    public static class AuthorWrapper {
        private int id;
        private String displayName;
        private String userName;
    }
}
