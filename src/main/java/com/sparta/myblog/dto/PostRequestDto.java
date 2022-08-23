package com.sparta.myblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto { // 테이블의 데이터에 접근할 때 완충재 역할
    private String author;
    private String contents;
    private String title;
    private String password;
}

