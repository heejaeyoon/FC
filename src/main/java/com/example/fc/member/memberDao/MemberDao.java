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
    MemberVo memberLogin(MemberVo memberVo);

    // dao == crud =>  xml에서 쿼리문 작성;
    // dto == 가져온 데이터를 읽고 쓰기 => getter, setter;
    // vo == 오직 읽기전용 => setter 없음.
}
