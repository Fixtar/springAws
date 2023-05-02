package com.fixtar.springboot.service.posts;

import com.fixtar.springboot.domain.posts.PostsRepository;
import com.fixtar.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService{

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto){
        return postsRepository.save(postsSaveRequestDto.toEntity())
                .getId();
    }


}
