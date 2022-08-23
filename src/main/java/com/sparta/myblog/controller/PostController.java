package com.sparta.myblog.controller;

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
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    // 비밀번호 확인
    @PostMapping("/posts/{id}")
    public Boolean CheckPassword(@PathVariable Long id, @RequestBody PwRequestDto requestDto) {
        return postService.check(id, requestDto);
    }


    // 전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<Post> getPostList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 개별 게시글 조회
    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postRepository.deleteById(id);
        return id;
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }
}
