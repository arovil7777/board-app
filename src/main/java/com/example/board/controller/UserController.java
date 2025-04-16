package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.model.User;
import com.example.board.service.EmailService;
import com.example.board.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // 회원가입 폼 요청
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User savedUser = userService.saveUser(user);
        emailService.sendVerificationEmail(savedUser);
        model.addAttribute("message", "회원가입이 완료되었습니다. 이메일을 확인해주세요.");
        return "auth/login";
    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // 이메일 인증 처리
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        boolean verified = emailService.verifyToken(token);
        if (verified) {
            model.addAttribute("message", "이메일 인증이 완료되었습니다. 로그인하세요.");
        } else {
            model.addAttribute("message", "유효하지 않거나 만료된 토큰입니다.");
        }
        return "auth/login";
    }

    // 비밀번호 재설정 폼
    @GetMapping("/reset-password")
    public String resetPasswordForm() {
        return "auth/reset-password";
    }

    // 비밀번호 재설정 처리 - 이메일로 재설정 링크 전송
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            // 재설정 이메일 전송
            emailService.sendResetPasswordEmail(user);
            model.addAttribute("message", "비밀번호 재설정 링크를 이메일로 전송했습니다.");
        } else {
            model.addAttribute("message", "입력하신 이메일 정보가 존재하지 않습니다.");
        }
        return "auth/login";
    }
}
