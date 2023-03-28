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

    public List<MemberVo> doMemberList() {
        List<MemberVo> list = new ArrayList<>();
        list = memberDao.doMemberList();
        return list;
    }
    /* insert , update는 리턴타입이 int이므로 리턴받지 않고 실행만 할 수 있음 */
    public void memberJoin(MemberVo memberVo){
         memberDao.memberJoin(memberVo);
    }

    public MemberVo memberLogin(MemberVo memberVo){
        return memberDao.memberLogin(memberVo);
    }
}
