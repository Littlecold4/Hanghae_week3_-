package com.sparta.week03.controller;

import com.sparta.week03.Service.BlogService;
import com.sparta.week03.domain.Blog;
import com.sparta.week03.domain.BlogRepository;
import com.sparta.week03.domain.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    @GetMapping("detail")
    public String detail(Model model){
        model.addAllAttributes("data","hello");
        return "detail";
    }


    @GetMapping("/api/blogs")
    public List<Blog> readBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/{id}")
    public Optional<Blog> readDetail(@PathVariable Long id){
        return blogRepository.findById(id);
    }




    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    @GetMapping("/detail")
    public String home(){
        return "detail.html";
    }


    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.update(id, requestDto);
        return id;
    }
}

