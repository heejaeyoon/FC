package com.example.fc.email.service.impl;

import com.example.fc.email.dao.verification.EmailVerificationDao;
import com.example.fc.email.model.EmailVerification;
import com.example.fc.email.service.EmailSenderService;
import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {


    private final JavaMailSender mailSender;

    private final EmailVerificationDao verification;

    private final EmailVerification emailVerification;


    /*ep관련 이메일*/
    //회원가입시 인증 이메일 발송
    @Override
    public void epSignUpEmail(String to, HashMap verifiedCode) {
        System.out.println("verifiedCode esi = " + verifiedCode);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("fctester96@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("FC 회원가입 인증 메일 입니다.");
        simpleMailMessage.setText("http://localhost:8077/epLogin?verifyingCode=" + verifiedCode.get(to));
        System.out.println("mailsender 메일보내기 성공 : " + mailSender);

        this.mailSender.send(simpleMailMessage);
    }

    //인증 코드 발급한 이메일과 인증 코드 저장
    @Override
    public void insertEpVerification(EpVo epVo, UUID code) {
        String email = epVo.getEmail();
        String strCode = code.toString();
        verification.insertEpVerification(email, strCode);
    }

    //이메일 인증 확인
    @Override
    public int epCheckVerification(EmailVerification emailVerification) {
        System.out.println("emailVerification = " + emailVerification);
        int result = verification.epCheckVerification(emailVerification);
        System.out.println(result);

        if(result ==1 ){
            return result;
        }

        return 0;
    }

    @Override
    public void epGotVerification(EmailVerification emailVerification) {
        verification.epGotVerification(emailVerification);
    }

    /*일반회원에게 이메일 보내기*/

    //인증코드 발송
    public void memberSignUpEmail(String to, HashMap verifiedCode) {
        System.out.println("verifiedCode esi = " + verifiedCode);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("fctester96@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("FC 회원가입 인증 메일 입니다.");
        simpleMailMessage.setText("http://localhost:8077/epLogin?verifyingCode=" + verifiedCode.get(to));
        System.out.println("mailsender 메일보내기 성공 : " + mailSender);

        this.mailSender.send(simpleMailMessage);
    }

    //이메일 인증 코드 및 발송 이메일 저장
    @Override
    public void insertMemberVerification(MemberVo memberVo, UUID code) {
        String email = memberVo.getEmail();
        String strCode = code.toString();
        verification.insertEpVerification(email, strCode);
    }

    //이메일 인증 확인
    @Override
    public int memberCheckVerification(EmailVerification emailVerification) {
        System.out.println("emailVerification = " + emailVerification);
        int result = verification.memberCheckVerification(emailVerification);
        System.out.println(result);

        if(result ==1 ){
            return result;
        }

        return 0;
    }

    @Override
    public void memberGotVerification(EmailVerification emailVerification) {
        verification.memberGotVerification(emailVerification);
    }

}