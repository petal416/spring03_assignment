package com.sparta.myblog.repository;

import com.sparta.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // username 테이블에서 username 을 찾음
}