package com.example.fc.member.memberController;

import com.example.fc.member.memberService.MemberService;
import com.example.fc.member.memberVo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller // 컨트롤러는 값만 넘겨주는 역할
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String member() {
        return "ex";
    }

    /* 회원가입 전 회원/기업 선택 */
    @GetMapping("/joinSelect")
    public String joinSelect(){
        return "joinForm";
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
        return "/loginForm";
    }

    @PostMapping("/login")
    public String memberLogin(MemberVo memberVo, HttpSession session) {
        //로그인시 받아온 값을 서비스로 넘겨줌
        
        
        String returnURL = "";
        if (session.getAttribute("memberLogin") != null) {
            //기존에 memberLogin 이란 세션 값이 존재한다면 기존 값을 제거해 준다.
            session.removeAttribute("memberLogin");
        }
        MemberVo vo = memberService.memberLogin(memberVo);
        System.out.println(vo);

        if (vo != null) {
            //세션에 memberLogin 이란 이름으로 memberVo 객체를 저장해 놓는다.
            session.setAttribute("memberLogin", vo);
            System.out.println("로그인 되었습니다 이메일과 비밀번호는 :" + vo);
            //로그인 성공시
            returnURL = "/ex";
            System.out.println("session 저장 값 : " + session.getAttribute( "memberLogin" ));
        } else {
            //로그인 실패시
            System.out.println("로그인 실패");
            returnURL = "LoginForm";
        }
        return returnURL;
    }

    
//    로그아웃
    @GetMapping("/logout")
    public String memberLogout(HttpSession session) {
        session.invalidate(); //세션 전체를 날려버린다.
//        session.removeAttribute("memberLogin"); //하나씩 날릴려면 이렇게 사용해도 된다
        return "/loginForm";
    }

//    마이페이지 호출
    @GetMapping("/myPage")
    public String myPage(HttpSession session) {
        session.getAttribute("memberLogin");
        System.out.println("세션값 -----------------" + session.getAttribute("memberLogin"));
        return "/member/memberMyPageForm";
    }
//    멤버 수정
    @GetMapping("/modify")
    public String memberModify(HttpSession session){
        session.getAttribute("memberLogin");
        return "/member/memberModifyForm";
    }

    @PostMapping("/modify")
    public String memberModify(MemberVo memberVo, HttpSession session){
        memberService.memberModify(memberVo);
        log.info("회원 수정 폼에서 입력받은 데이터: {}", memberVo);
        session.setAttribute("memberLogin",memberVo);
        return "/member/memberMyPageForm";
    }

    @PostMapping("/delete")
    public String memberDelete(MemberVo memberVo,HttpSession session){

        memberService.memberDelete(memberVo);
                  
        session.removeAttribute("memberLogin");
        log.info("--------------------들어온 값" + memberVo.getId());
        return "/loginForm";
    }
}
