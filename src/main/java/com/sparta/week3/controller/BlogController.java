package com.sparta.week3.controller;

import com.sparta.week3.domain.BlogRepository;
import com.sparta.week3.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;
}