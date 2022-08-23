package com.sparta.myblog.controller;

import com.sparta.myblog.dto.PostResponseDto;
import com.sparta.myblog.dto.PwRequestDto;
import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.dto.PostRequestDto;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public PostResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 비밀번호 확인
    @PostMapping("/posts/{id}")
    public PostResponseDto<?> validateAuthorByPassword(@PathVariable Long id, @RequestBody PwRequestDto password) {
        return postService.validateAuthorByPassword(id, password);
    }


    // 전체 게시글 목록 조회
    @GetMapping("/posts")
    public PostResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    // 개별 게시글 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public PostResponseDto<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public PostResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }
}
