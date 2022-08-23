package com.sparta.myblog.service;

import com.sparta.myblog.dto.PostResponseDto;
import com.sparta.myblog.dto.PwRequestDto;
import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor // final 선언할때 스프링에게 알려줌
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class PostService {
    private final PostRepository postRepository; // final : 서비스에게 꼭 필요함을 명시

//    @Transactional // 업데이트를 할 때, DB에 반영이 되는 것을 스프링에게 알려줌
//    public Long update(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
//        );
//        post.update(requestDto);
//        return post.getId();
//    }
//
//    @Transactional
//    public Boolean check(Long id, PwRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
//        );
//        return post.getPassword().equals(requestDto.getPassword());
//    }

    @org.springframework.transaction.annotation.Transactional
    public PostResponseDto<?> createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return PostResponseDto.success(post);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public PostResponseDto<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return PostResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        return PostResponseDto.success(optionalPost.get());
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public PostResponseDto<?> getAllPost() {
        return PostResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }

    @org.springframework.transaction.annotation.Transactional
    public PostResponseDto<Post> updatePost(Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return PostResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        Post post = optionalPost.get();
        post.update(requestDto);

        return PostResponseDto.success(post);
    }

    @org.springframework.transaction.annotation.Transactional
    public PostResponseDto<?> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return PostResponseDto.fail("NOT_FOUND", "post id is not exist");
        }
        Post post = optionalPost.get();
        postRepository.delete(post);
        return PostResponseDto.success(true);
    }

    @Transactional(readOnly = true)
    public PostResponseDto<?> validateAuthorByPassword(Long id, PwRequestDto password) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return PostResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        Post post = optionalPost.get();

        if (!post.getPassword().equals(password.getPassword())) {
            return PostResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return PostResponseDto.success(true);
    }
}
