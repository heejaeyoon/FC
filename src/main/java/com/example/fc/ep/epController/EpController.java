package com.example.fc.ep.epController;

import com.example.fc.ep.epService.EpService;
import com.example.fc.ep.epVo.EpVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class EpController {

    @Autowired
    EpService epService;


    /* 회원가입 */
    @GetMapping("/epInsert")
    public String epJoin(){
        return "/ep/enterpriseJoinForm";
    }
    @PostMapping("/epInsert")
    public String memberJoin(EpVo epVo){
        log.info("회원가입 폼에서 입력받은 데이터: {}",epVo);
        epService.epJoin(epVo);

        return "/ex";
    }

    /* 로그인 */
    @GetMapping("/epLogin")
    public String epLogin() {
        return "loginForm";
    }

    @PostMapping("/epLogin")

    public String epLogin(EpVo epVo, HttpSession Session){
        if (Session.getAttribute("epLogin") != null){
            Session.removeAttribute("epLogin");
        }
        EpVo vo = epService.epLogin(epVo);
        System.out.println("vo = " + vo);

        if (vo != null) {
            Session.setAttribute("epLogin",vo);
            System.out.println("로그인이 성공하였습니다"+vo);
            return "/ex";
        }else{
            System.out.println("로그인 실패");
            return "/loginForm";
        }

    }
    @GetMapping("/epModify")
    public String epModify(){
        System.out.println("epService = " + epService);
        return "/ep/epModify";
    }
    @PostMapping("/epModify")
    public String epModify(EpVo epVo, HttpSession Session){
        int vo = epService.epUpdate(epVo);
        Session.setAttribute("epModify",vo);
        System.out.println("업데이트 성공했습니다");
            return "/ex";
    }
    @PostMapping("/epDelete")
    public String epDelete(EpVo epVo, HttpSession Session, Model model){
        int vo = epService.epDelete(epVo);
        System.out.println("지우기~!! 성공했습니다");
        Session.removeAttribute("epLogin");

        return "/ex";
    }

}