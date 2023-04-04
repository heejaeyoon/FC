package com.example.fc.member.memberService;

import com.example.fc.member.memberDao.MemberDao;
import com.example.fc.member.memberVo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;


    /* insert , update는 리턴타입이 int이므로 리턴받지 않고 실행만 할 수 있음 */
    /* 회원 가입 */
    public void memberJoin(MemberVo memberVo){
        memberDao.memberJoin(memberVo);
    }

    /* 회원 수정 */
    public void memberModify(MemberVo memberVo){
        memberDao.memberModify(memberVo);
    }

    /* 회원 삭제 */
    public void memberDelete(MemberVo memberVo){
        memberDao.memberDelete(memberVo);
    }

    /* 로그인 */
    public MemberVo memberLogin(MemberVo memberVo){
        return memberDao.memberLogin(memberVo);
    }
}
