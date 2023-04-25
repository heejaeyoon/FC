package com.example.fc.support.supportService;

import com.example.fc.member.memberDao.MemberJobHuntingFilesDao;
import com.example.fc.support.supportDao.NoticeDao;
import com.example.fc.support.supportDao.NoticeFilesDao;
import com.example.fc.support.supportVo.NoticeFilesVo;
import com.example.fc.support.supportVo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;
    private final NoticeFilesDao noticeFilesDao;

    @Value("${saveNoticeFile}")
    String savePath;

    //    공지사항 리스트
    public List<NoticeVo> noticeAllList() {
        return noticeDao.noticeAllList();
    }

    //  공지사항 이미지리스트
    public List<NoticeFilesVo> noticeFilesList(int noticeBoard){
        return noticeFilesDao.noticeFilesList(noticeBoard);
    }

    //    공지사항 등록, TIMESTAMP 를 사용함으로써 게시글 저장 날짜 자동 생성(now()사용 x)
    public int noticeInsert(NoticeVo noticeVo, MultipartFile[] file) throws IOException {

        System.out.println("file.length = " + file.length);
        //파일이 미첨부일때 바로 게시글 등록
        if (file[0].isEmpty() || file[0].getOriginalFilename() == null || file[0].getOriginalFilename().equals("")) {
            noticeVo.setFileAttached(0); // 첨부된 파일이 없다.

            int result = noticeDao.noticeInsert(noticeVo); // 게시글 등록

            int noticeBoard = noticeDao.findNoticeBoard(noticeVo);

            return 0;

        } else { //파일이 첨부 된 경우
            noticeVo.setFileAttached(1); // 파일 첨부값을 1로 변경
            System.out.println("noticeVo = " + noticeVo.getFileAttached());
            int result = noticeDao.noticeInsert(noticeVo); // 파일저장 == 게시글 저장
            int noticeBoard = noticeDao.findNoticeBoard(noticeVo);

            System.out.println("noticeBoard1 = " + noticeBoard);
            // 파일 관련 코드
            for (MultipartFile getFile : file) {
                UUID uuid = UUID.randomUUID(); // 중복되지 않은 값
                String originalFileName = getFile.getOriginalFilename(); //파일의 본래 파일명
                String storedFileName = uuid + "_" + originalFileName; // 파일의 중복되지 않는 값을 더한 파일명

//          String savePath = "files"; //파일경로, properies도 함께 참고
                File saveFile = new File(savePath, storedFileName);
                getFile.transferTo(saveFile);//파일이동

//            파일 저장
                NoticeFilesVo files = NoticeFilesVo.toSetterFilesVo((long) noticeBoard, originalFileName, storedFileName, savePath);
                int resultFiles = noticeFilesDao.insertNoticeFiles(files);
                System.out.println("resultFiles = " + resultFiles);

            }
        }
        return 1;
    }

    /*   System.out.println("서비스 게시판 저장 result = " + result);*/

    //    공지사항 수정
    public void noticeModify(NoticeVo noticeVo) {
        noticeDao.noticeModify(noticeVo);
    }

    //    공지사항 상세 조회
    public NoticeVo noticeOneList(int notice_board) {
        return noticeDao.noticeOneList(notice_board);
    }

    //    공지사항 삭제
    public void noticeDelete(int notice_board) {
        noticeDao.noticeDelete(notice_board);
    }
}
