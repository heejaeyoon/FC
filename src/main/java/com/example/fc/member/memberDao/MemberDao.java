package com.example.fc.member.memberDao;

import com.example.fc.member.memberVo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    public List<MemberVo> doMemberList();

    /* 회원가입 */
    public int memberJoin(MemberVo memberVo);

    /* 로그인 */
    public MemberVo memberLogin(MemberVo memberVo);
}
