package com.sparta.myblog.repository;

import com.sparta.myblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    List<Post> findAllByOrderByModifiedAtDesc(); // 모두 불러와 id에 대해 내림차순 정렬
}