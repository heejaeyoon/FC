package com.example.fc.support.supportController;

import com.example.fc.support.supportDao.PersonalDao;
import com.example.fc.support.supportService.PersonalService;
import com.example.fc.support.supportService.SupportService;
import com.example.fc.support.supportVo.PersonalVo;
import com.example.fc.support.supportVo.SupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonalController {

    @Autowired
    PersonalService personalService;

    //
//    /* 게시판 리스트 */
//    @GetMapping("/noticeAllList")
//    public String noticeList(Model model) {
//        List<SupportVo> noticeList = personalService.noticeAllList();
//        System.out.println("noticeList control = " + noticeList);
//        model.addAttribute("noticeList", noticeList);
//        return "/support/noticeListForm";
//    }
//
//    /* 게시판 글 쓰기*/
    @ResponseBody//어노테이션을 사용하여 페이지에 데이터'만'을 전달할 수 있다.
    @GetMapping("/personalInsert")
    public String noticeInsert(HttpSession session) {
        String result;
        if (session.getAttribute("epLogin") != null || session.getAttribute("memberLogin") != null) {
            System.out.println("session31311312131213213131 = " + session);

            result = "<script>location.href='/personalQuestion';</script>";
        } else {
            result = "<script>alert('올바르지 않은 정보입니다.');location.href='/login';</script>";
        }

        return result;
    }
    @GetMapping("/personalQuestion")
    public String personalQuestion() {
        return "support/personalQuestion";
    }

    @PostMapping("/personalInquire")
    public String personalInquire(PersonalVo personalVo, HttpSession session){
        personalService.personalInquire(personalVo);
        System.out.println("personalVo 입력성공하였습니다. === " + personalVo);
        return "/ex";
    }

//
//    @PostMapping("/noticeInsert")
//    public String noticeInsert(SupportVo supportVo, HttpSession session) {
//        session.getAttribute("memberLogin");
//        System.out.println(session);
//        personalService.noticeInsert(supportVo);
//        System.out.println("넘어 온 값: " + supportVo);
//        return "redirect:/noticeAllList";
//    }
//
//
//    //   공지사항 상세 조회
//    @GetMapping("/noticeOneList")
//    public String noticeOneList(SupportVo supportVo, Model model, int noticeBoard) {
//        supportVo = personalService.noticeOneList(noticeBoard);
//        model.addAttribute("noticeList", supportVo);
//        System.out.println("-===========================" + noticeBoard);
//        System.out.println("-===========================" + supportVo);
//        return "/support/noticeDetailForm";
//    }
//
//
//    //    공지사항 수정
//    @GetMapping("/noticeModify")
//    public String noticeModify() {
//        return "/support/noticeListForm";
//    }
//
//    @PostMapping("/noticeModify")
//    public String noticeModify(Model model, SupportVo supportVo) {
//        personalService.noticeModify(supportVo);
//        System.out.println("=================================!!!:" + supportVo);
//        model.addAttribute("noticeList", supportVo);
//        System.out.println("============================" + supportVo);
//        return "redirect:/noticeAllList";
//    }
////    공지사항 삭제
//    @PostMapping("/noticeDelete")
//    public String noticeDelete(int noticeBoard){
//        System.out.println("삭제되기전 값 " + noticeBoard);
//        personalService.noticeDelete(noticeBoard);
//        System.out.println("삭제되었습니다 " + noticeBoard);
//        return "redirect:/noticeAllList";
}
//
//}
