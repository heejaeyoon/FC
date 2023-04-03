package com.example.fc.member.memberService;

import com.example.fc.ep.epVo.EpVo;
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

    public int idCheck(MemberVo memberVo) throws Exception{
        System.out.println("epVo service = " + memberVo);
        int result = memberDao.idCheck(memberVo);return result;}

    public MemberVo memberPasswordCheck(MemberVo memberVo){return  memberDao.memberPasswordCheck(memberVo);}

    public MemberVo memberEmailCheck(MemberVo memberVo){return  memberDao.memberEmailCheck(memberVo);}
}
