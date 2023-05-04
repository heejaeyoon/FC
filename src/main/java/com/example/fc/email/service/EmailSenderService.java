package com.example.fc.email.service;

import com.example.fc.email.model.EmailVerification;
import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;

import java.util.HashMap;
import java.util.UUID;

public interface EmailSenderService {
    //void signUpEmail(String from, String to, String subject, String message);
    
    /*ep관련 이메일*/
    //인증 이메일 발송
    void epSignUpEmail(String to, HashMap verifiedCode);

    //인증 코드를 발급한 이메일과 인증코드 저장
    void insertEpVerification(EpVo epVo, UUID code);

    //이메일 인증
    int epCheckVerification(EmailVerification emailVerification);

    //이메일 인증을 완료
    void epGotVerification(EmailVerification emailVerification);


    /*member관련 이메일*/

    //인증 이메일 발송
    void memberSignUpEmail(String email, HashMap<String, UUID> hashMap);

    //인증 코드를 발급한 이메일과 인증코드 저장
    void insertMemberVerification(MemberVo memberVo, UUID code);

    //이메일 인증
    int memberCheckVerification(EmailVerification emailVerification);

    //이메일 인증을 완료
    void memberGotVerification(EmailVerification emailVerification);
}



