package com.example.fc.support.supportController;

import com.example.fc.support.supportService.EpOneToOneService;
import com.example.fc.support.supportService.MemberOneToOneService;
import com.example.fc.support.supportVo.EpOneToOneVo;
import com.example.fc.support.supportVo.MemberOneToOneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OneToOneController {

    @Autowired
    MemberOneToOneService memberOneToOneService;
    @Autowired
    EpOneToOneService epOneToOneService;

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
    public String personalQuestion(HttpSession session) {
        if (session.getAttribute("epLogin") !=null) {
            return "support/epOneToOneQuestion";

        } else if (session.getAttribute("memberLogin") !=null) {
            return "/support/memberOneToOneQuestion";
        }else {
            return "ex";
        }
    }

    //insert
    @PostMapping("/personalInquire")
    public String personalInquire(MemberOneToOneVo memberOneToOneVo, EpOneToOneVo epOneToOneVo, HttpSession session) {

        if (session.getAttribute("epLogin") != null) {
            System.out.println("epOneToOneVo = " + epOneToOneVo);
            epOneToOneService.epPersonalInquire(epOneToOneVo);
            System.out.println("personalVo 입력성공하였습니다. === " + memberOneToOneVo);

        } else if (session.getAttribute("memberLogin") != null) {
            memberOneToOneService.mPersonalInquire(memberOneToOneVo);
            System.out.println("personalVo 입력성공하였습니다. === " + memberOneToOneVo);
        }
            return "/ex";
    }

}
