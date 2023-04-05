package com.example.fc.support.supportDao;


import com.example.fc.support.supportVo.PersonalVo;
import com.example.fc.support.supportVo.SupportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalDao {
    //    공지사항 리스트
    List<SupportVo> noticeAllList();

    //    공지사항 등록
    int personalInquire(PersonalVo personalVo);

    //    공지사항 수정
    int noticeModify(PersonalVo personalVo);

    //게시판 상세 조회
    SupportVo noticeOneList(int notice_board);

//    게시판 삭제
    int noticeDelete(int notice_board);

}
