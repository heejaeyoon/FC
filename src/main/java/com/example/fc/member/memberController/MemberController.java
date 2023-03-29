package com.example.fc.member.memberController;

import com.example.fc.member.memberService.MemberService;
import com.example.fc.member.memberVo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String member() {

        return "ex";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "/member/memberMyPageForm";

    }

    /* 회원가입 */
    @GetMapping("/insert")
    public String memberJoin() {
        return "/member/memberJoinForm";
    }

    @PostMapping("/insert")
    public String memberJoin(MemberVo memberVo) {
        log.info("회원가입 폼에서 입력받은 데이터: {}", memberVo);
        memberService.memberJoin(memberVo);

        return "/member/memberForm2";

    }

    /* 로그인 */
    @GetMapping("/login")

    public String memberLogin() {
        return "/member/memberLoginForm";
    }

    @PostMapping("/login")
    public String memberLogin(MemberVo memberVo, HttpSession session, Model model) {
        String returnURL = "";
        if (session.getAttribute("memberLogin") != null) {
            //기존에 memberLogin 이란 세션 값이 존재한다면 기존 값을 제거해 준다.
            session.removeAttribute("memberLogin");
        }
        MemberVo vo = memberService.memberLogin(memberVo);
        model.addAttribute("memberLogined", vo);
        System.out.println(vo);

        if (vo != null) {
            //세션에 memberLogin 이란 이름으로 memberVo 객체를 저장해 놓는다.
            session.setAttribute("memberLogin", vo);
            System.out.println("로그인 되었습니다 이메일과 비밀번호는 :" + vo);
            //로그인 성공시
            returnURL = "/ex";
            System.out.println(session.getAttribute("memberLogin"));
        } else {
            //로그인 실패시
            System.out.println("로그인 실패");
            returnURL = "/member/memberLoginForm";
        }
        return returnURL;
    }

    @GetMapping("/logout")
    public String memberLogout(HttpSession session) {
        session.invalidate(); //세션 전체를 날려버린다.
//        session.removeAttribute("memberLogin"); //하나씩 날릴려면 이렇게 사용해도 된다
        return "/member/memberLoginForm";


    }
}
