package com.sparta.myblog.service;

import com.sparta.myblog.dto.PwRequestDto;
import com.sparta.myblog.entity.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final 선언할때 스프링에게 알려줌
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class PostService {
    private final PostRepository postRepository; // final : 서비스에게 꼭 필요함을 명시

    @Transactional // 업데이트를 할 때, DB에 반영이 되는 것을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

    @Transactional
    public Boolean check(Long id, PwRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return post.getPassword().equals(requestDto.getPassword());
    }
}
