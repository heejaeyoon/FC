package com.example.fc.support.supportService;

import com.example.fc.support.supportDao.PersonalDao;
import com.example.fc.support.supportDao.SupportDao;
import com.example.fc.support.supportVo.PersonalVo;
import com.example.fc.support.supportVo.SupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    PersonalDao personalDao;

    //    공지사항 리스트
    public List<SupportVo> noticeAllList() {
        return personalDao.noticeAllList();
    }
    //    공지사항 등록
    public void personalInquire(PersonalVo personalVo) {
        personalDao.personalInquire(personalVo);
    }

    //    공지사항 수정
//    public void noticeModify(SupportVo supportVo) {
//        personalDao.noticeModify(supportVo);
//    }
//    공지사항 상세 조회
    public SupportVo noticeOneList(int notice_board){
        return personalDao.noticeOneList(notice_board);
    }
//    공지사항 삭제
    public void noticeDelete(int notice_board){
        personalDao.noticeDelete(notice_board);
    }
}
