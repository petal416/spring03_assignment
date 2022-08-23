package com.sparta.myblog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig {
// Spring security 5.7버전 이상부터 deprecated 되어서 WebSecurityConfigurerAdapter 대신에 아래처럼 구현
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        // 어떤 요청이든 '인증'
                        .anyRequest().authenticated() //인증
                )
                    // 로그인 기능 허용
                    .formLogin()
                    .loginPage("/user/login") // 로그인 페이지 주소
                    .defaultSuccessUrl("/") // 로그인이 성공하면 이동할 주소
                    .failureUrl("/user/login?error") // 로그인에 실패했을 때
                    .permitAll()
                .and()
                    //로그아웃 기능 허용
                    .logout()
                    .permitAll(); // 로그아웃에 관련된 기능을 허용

        return http.build();
    }
}