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

//    @GetMapping("/hello") // "/hello"를 입력하면 method가 호출됨
//    public String hello(Model model) {
//        model.addAttribute("data","hello!!");
//        return "hellsadsadsao";
//    }


    @GetMapping("/api/blogs")
    public List<Blog> readBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/{id}")
    public Blog readDetail(@PathVariable Long id){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new NullPointerException()
        );
        return blog;
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

