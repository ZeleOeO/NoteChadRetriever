package com.zele.notechadretriever.controllers;

import com.zele.notechadretriever.entities.DefaultAPIResponse;
import com.zele.notechadretriever.entities.pojo.AuthorAPIResponse;
import com.zele.notechadretriever.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<DefaultAPIResponse<List<AuthorAPIResponse.AuthorWrapper>>> getAuthors(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            return authorService.getAuthor(token);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(new DefaultAPIResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
}
