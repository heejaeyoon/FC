package com.example.fc.memberController;

import com.example.fc.service.MemberService;
import com.example.fc.vo.Vo_member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String member(){
        return "ex";
    }

    @GetMapping("/member")
    public String memberForm(){
        return "/member/memberJoinForm";
    }

    @GetMapping("/member2")
    public String memberForm2(Model model) {
        List<Vo_member> list = new ArrayList<>();
        list = memberService.doMemberList();

        log.info("vo_member");
        for(Vo_member vo_member : list) {
            log.info(vo_member.getName());
            log.info(vo_member.getEmail());
            log.info(vo_member.getPassword());
        }
        model.addAttribute("list",list);
        return "/member/memberForm2";
    }

    /* 회원가입 */
    @GetMapping("/insert")
    public String doIns(){
        return "/member/memberJoinForm";
    }

//    @PostMapping("/insert")
//    public String doInseExe(){
//
//    }

}
