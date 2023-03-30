package com.example.fc.member.memberDao;

import com.example.fc.member.memberVo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    /* 회원가입 */
    public int memberJoin(MemberVo memberVo);

    /* 회원수정 */
    public int memberModify(MemberVo memberVo);

    /* 회원탈퇴 */
    public int memberDelete(MemberVo memberVo);

    /* 로그인 */
    MemberVo memberLogin(MemberVo memberVo);
}
