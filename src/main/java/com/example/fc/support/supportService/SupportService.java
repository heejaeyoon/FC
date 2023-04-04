package com.example.fc.support.supportService;

import com.example.fc.support.supportDao.SupportDao;
import com.example.fc.support.supportVo.SupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SupportService {

    @Autowired
    SupportDao supportDao;

    //    공지사항 리스트
    public List<SupportVo> noticeAllList() {
        return supportDao.noticeAllList();
    }
    //    공지사항 등록
    public void noticeInsert(SupportVo supportVo) {
        supportDao.noticeInsert(supportVo);
    }

    //    공지사항 수정
    public void noticeModify(SupportVo supportVo) {
        supportDao.noticeModify(supportVo);
    }
//    공지사항 상세 조회
    public SupportVo noticeOneList(int notice_board){
        return supportDao.noticeOneList(notice_board);
    }
//    공지사항 삭제
    public void noticeDelete(int notice_board){
        supportDao.noticeDelete(notice_board);
    }
}
