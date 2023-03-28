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
    public String member(){
        return "ex";
    }

//    @GetMapping("/member")
//    public String memberForm(){
//        return "/member/memberJoinForm";
//    }

//    @GetMapping("/member2")
//    public String memberForm2(Model model) {
//        List<Vo_member> list = new ArrayList<>();
//        list = memberService.doMemberList();
//
//        log.info("vo_member");
//        for(Vo_member vo_member : list) {
//            log.info(vo_member.getName());
//            log.info(vo_member.getEmail());
//            log.info(vo_member.getPassword());
//        }
//        model.addAttribute("list",list);
//        return "/member/memberForm2";
//    }

    /* 회원가입 */
    @GetMapping("/insert")
    public String memberJoin(){
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
    public MemberVo login(MemberVo memberVo){
        System.out.println(memberVo);
        return memberService.Login(memberVo);
    }


    @GetMapping("/memberJobHunting")
    public String getJobHunting(){

        return "member/memberJobHunting";
    }
}
