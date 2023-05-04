package com.example.fc.email.service.impl;

import com.example.fc.email.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;


    @Override
    public void signUpEmail(String to) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("fctester96@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("FC 회원가입 인증 메일 입니다.");
        simpleMailMessage.setText("");
        System.out.println("mailsender 메일보내기 성공 : "+mailSender);

        this.mailSender.send(simpleMailMessage);
    }


}