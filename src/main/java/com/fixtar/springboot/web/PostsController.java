package com.fixtar.springboot.web;

import com.fixtar.springboot.service.posts.PostsService;
import com.fixtar.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController{

    private final PostsService postsService;

    @PostMapping("api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto){
        return postsService.save(postsSaveRequestDto);
    }

}
