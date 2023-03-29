package com.example.fc.member.memberController;

import com.example.fc.member.memberService.MemberService;
import com.example.fc.member.memberVo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String member(){
        return "ex";
    }

    /* 회원가입 */
    @GetMapping("/insert")
    public String memberJoin(HttpSession session){
        return "/member/memberJoinForm";
    }

    @PostMapping("/insert")
    public String memberJoin(MemberVo memberVo){
        log.info("회원가입 폼에서 입력받은 데이터: {}",memberVo);
        memberService.memberJoin(memberVo);

        return "/member/memberForm2";

    }

    /* 로그인 */
    @GetMapping("/login")
    public String login() {
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberVo memberVo, HttpSession Session, Model model){
        if (Session.getAttribute("memberLogin") != null){
            Session.removeAttribute("memberLogin");
        }
        MemberVo vo = memberService.Login(memberVo);
        model.addAttribute("memberLogin",vo);
        System.out.println("vo = " + vo);

        if (vo != null) {
            Session.setAttribute("memberLogin",vo);
            System.out.println("로그인이 성공하였습니다"+vo);
            return "/ex";
        }else{
            System.out.println("로그인 실패");
            return "/member/loginForm";
        }
        }
        @GetMapping("/logout")
        public String logout(HttpSession Session) {
            Session.removeAttribute("memberLogin");
            return "/ex";

    }
    @GetMapping("/modify")
    public String modify() {
        return "/ep/aaa";
    }
}
