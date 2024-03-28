package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/users/{userId}/blogs")
    public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@RequestParam String userId, @RequestBody BlogRequest blogRequest) {
        return blogService.createBlog(userId, blogRequest);
    }
}

	
