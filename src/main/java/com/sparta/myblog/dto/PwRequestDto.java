package com.sparta.myblog.dto;

import lombok.Getter;
import lombok.Setter;

// password값만 확인하기 위해 사용하는 Dto
@Getter
@Setter
public class PwRequestDto {
    private String password;
}