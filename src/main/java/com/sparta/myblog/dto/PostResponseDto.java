package com.sparta.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto<T> {
    private boolean success;
    private T data;
    private Error error;

    public static <T> PostResponseDto<T> success(T data) {
        return new PostResponseDto<>(true, data, null);
    }

    public static <T> PostResponseDto<T> fail(String code, String message) {
        return new PostResponseDto<>(false, null, new Error(code, message));
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }

}