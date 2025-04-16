package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.board.service.UserService;

@Configuration
public class SecurityConfig {
    // 비밀번호 암호화를 위한 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 커스텀 UserDetailsService: 회원 정보를 로드
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            var user = userService.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found: " + username);
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    // 이메일 인증이 완료된 사용자만 활성화
                    .disabled(!user.isEnabled())
                    .roles("USER")
                    .build();
        };
    }

    // URL 접근 및 로그인/로그아웃 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // 정적 리소스 및 회원가입, 이메일 인증 URL은 누구나 접근 가능
                .requestMatchers("/css/**", "/js/**", "/images/**", "/register", "/verify", "/reset-password")
                .permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/boards", true)
                        .permitAll())
                // .oauth2Login(oauth2 -> oauth2.loginPage("/login").defaultSuccessUrl("/",
                // true))
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}
