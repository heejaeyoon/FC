package com.example.fc.support.supportDao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupportDao {
    //    공지사항 리스트
    List<SupportVo> noticeAllList();

    //    공지사항 등록
    int noticeInsert(SupportVo supportVo);

    //    공지사항 수정
    int noticeModify(SupportVo supportVo);

    //게시판 상세 조회
    SupportVo noticeOneList(int notice_board);

//    게시판 삭제
    int noticeDelete(int notice_board);

}
