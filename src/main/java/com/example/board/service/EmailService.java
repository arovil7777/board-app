package com.example.board.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.board.model.User;
import com.example.board.model.VerificationToken;
import com.example.board.repository.VerificationTokenRepository;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    // 이메일 인증 토큰 생성 및 메일 전송
    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1)); // 1일 유효
        tokenRepository.save(verificationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[파이웍스] 이메일 인증");
        mailMessage.setText("아래 링크를 클릭하여 이메일 인증을 완료하세요: " + "http://localhost:8080/verify?token=" + token);
        mailSender.send(mailMessage);
    }

    // 비밀번호 재설정용 토큰 생성 및 이메일 전송
    public void sendResetPasswordEmail(User user) {
        String token = UUID.randomUUID().toString();
        // VerifcationToken 엔티티를 재사용 (비밀번호 재설정 토큰으로 활용)
        VerificationToken resetToken = new VerificationToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(2)); // 2시간 유효
        tokenRepository.save(resetToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[파이웍스] 비밀번호 재설정");
        mailMessage.setText("아래 링크를 클릭하여 비밀번호 재설정을 진행하세요: " + "http://localhost:8080/reset-password?token=" + token);
        mailSender.send(mailMessage);
    }

    // 토큰 검증 및 사용자 활성화
    public boolean verifyToken(String token) {
        var optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            VerificationToken verificationToken = optionalToken.get();
            if (verificationToken.getExpiryDate().isAfter(LocalDateTime.now())) {
                var user = verificationToken.getUser();
                user.setEnabled(true);
                tokenRepository.delete(verificationToken);
                return true;
            }
        }
        return false;
    }

}
