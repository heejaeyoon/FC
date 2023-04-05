package com.example.fc.support.supportController;

import com.example.fc.support.supportService.SupportService;
import com.example.fc.support.supportVo.SupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SupportController {

    @Autowired
    SupportService supportService;


    /* 게시판 리스트 */
    @GetMapping("/noticeAllList")
    public String noticeList(Model model) {
        List<SupportVo> noticeList = supportService.noticeAllList();
        System.out.println("noticeList control = " + noticeList);
        model.addAttribute("noticeList", noticeList);
        return "/support/noticeListForm";
    }

    /* 게시판 글 쓰기*/
    @GetMapping("/noticeInsert")
    public String noticeInsert() {
        return "/support/noticeInsertForm";
    }

    @PostMapping("/noticeInsert")
    public String noticeInsert(SupportVo supportVo, HttpSession session) {
        session.getAttribute("memberLogin");
        System.out.println(session);
        supportService.noticeInsert(supportVo);
        System.out.println("넘어 온 값: " + supportVo);
        return "redirect:/noticeAllList";
    }


    //   공지사항 상세 조회
    @GetMapping("/noticeOneList")
    public String noticeOneList(SupportVo supportVo, Model model, int noticeBoard) {
        supportVo = supportService.noticeOneList(noticeBoard);
        model.addAttribute("noticeList", supportVo);
        System.out.println("-===========================" + noticeBoard);
        System.out.println("-===========================" + supportVo);
        return "/support/noticeDetailForm";
    }


    //    공지사항 수정
    @GetMapping("/noticeModify")
    public String noticeModify() {
        return "/support/noticeListForm";
    }

    @PostMapping("/noticeModify")
    public String noticeModify(Model model, SupportVo supportVo) {
        supportService.noticeModify(supportVo);
        System.out.println("=================================!!!:" + supportVo);
        model.addAttribute("noticeList", supportVo);
        System.out.println("============================" + supportVo);
        return "redirect:/noticeAllList";
    }
//    공지사항 삭제
    @PostMapping("/noticeDelete")
    public String noticeDelete(int noticeBoard){
        System.out.println("삭제되기전 값 " + noticeBoard);
        supportService.noticeDelete(noticeBoard);
        System.out.println("삭제되었습니다 " + noticeBoard);
        return "redirect:/noticeAllList";
    }

}
