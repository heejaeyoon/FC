package com.example.fc.ep.epDao;

import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EPDao {

    public List<EpVo> doEPList();

    /* 회원가입 */
    public int epJoin(EpVo epVo);

    /* 로그인 */
    EpVo epLogin(EpVo epVo);

    // dao == crud =>  xml에서 쿼리문 작성;
    // dto == 가져온 데이터를 읽고 쓰기 => getter, setter;
    // vo == 오직 읽기전용 => setter 없음.
}