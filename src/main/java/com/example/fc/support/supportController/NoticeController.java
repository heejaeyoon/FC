package com.example.fc.support.supportController;

import com.example.fc.support.supportService.NoticeService;
import com.example.fc.support.supportVo.NoticeFilesVo;
import com.example.fc.support.supportVo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {
    @Value("${saveNoticeResourceFile}")
    String savePath;
    @Value("${upload}")
    String upload;
    public final NoticeService noticeService;

    //     공지사항 리스트
    @GetMapping("/noticeAllList")
    public String noticeList(Model model) {
        List<NoticeVo> noticeList = noticeService.noticeAllList();
        System.out.println("noticeList control = " + noticeList);
        model.addAttribute("noticeList", noticeList);


        return "/support/noticeListForm";
    }

    //    공지사항 글 쓰기
    @GetMapping("/noticeInsert")
    public String noticeInsert() {
        return "/support/noticeInsertForm";
    }

    @PostMapping("/noticeInsert")
    public String noticeInsert(NoticeVo noticeVo, HttpSession session, MultipartFile[] file) throws IOException {
        System.out.println("uploadResult = " + file[0].getResource());
        session.getAttribute("memberLogin");
        System.out.println(session);
        int uploadResult = noticeService.noticeInsert(noticeVo, file);
        System.out.println("컨트롤러 게시판 저장 result = " + uploadResult);

        if (uploadResult == 1) {
            return "redirect:/noticeAllList"; // redirect는 웹 페이지 경로 웹 페이지를 보여줌
        }
        return "redirect:/noticeAllList"; // return는 html경로 => 순수 html만 보여줘서 오류
    }


    //   공지사항 상세 조회
    @GetMapping("/noticeOneList")
    public String noticeOneList(Model model, int noticeBoard) {
        NoticeVo noticeVo = noticeService.noticeOneList(noticeBoard);
        List<NoticeFilesVo> noticeFilesVo = noticeService.noticeFilesList(noticeBoard);
        model.addAttribute("noticeList", noticeVo);
        model.addAttribute("noticeFilesList", noticeFilesVo);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + noticeFilesVo);
        System.out.println("-===========================" + noticeBoard);
        System.out.println("-===========================" + noticeVo);
        return "/support/noticeDetailForm";
    }


    //    공지사항 수정
    @GetMapping("/noticeModify")
    public String noticeModify() {
        return "/support/noticeListForm";
    }

    @PostMapping("/noticeModify")
    public String noticeModify(Model model, NoticeVo noticeVo) {
        noticeService.noticeModify(noticeVo);
        System.out.println("=================================!!!:" + noticeVo);
        model.addAttribute("noticeList", noticeVo);
        System.out.println("============================" + noticeVo);
        return "redirect:/noticeAllList";
    }

    //    공지사항 삭제
    @PostMapping("/noticeDelete")
    public String noticeDelete(int noticeBoard) {
        System.out.println("삭제되기전 값 " + noticeBoard);
        noticeService.noticeDelete(noticeBoard);
        System.out.println("삭제되었습니다 " + noticeBoard);
        return "redirect:/noticeAllList";
    }

}
