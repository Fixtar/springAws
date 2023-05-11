package com.Fixtar.springboot.web.domain.posts;

import com.fixtar.springboot.domain.posts.Posts;
import com.fixtar.springboot.domain.posts.PostsRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostsRepositoryTest{
    
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "test title";
        String content = "test content";

        postsRepository.save(Posts.builder().title(title).content(content).author("Fixtar").build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                                     .title("title")
                                     .content("content")
                                     .author("author")
                                     .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>> createDate = " + posts.getCreatedDate() + ", modifiedDate = "+ posts.getModifiedDate());

        Assertions.assertThat(posts.getCreatedDate()).isBefore(now);
        Assertions.assertThat(posts.getModifiedDate()).isBefore(now);
    }
    
    
}
