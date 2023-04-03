package com.example.fc.ep.epController;

import com.example.fc.ep.epService.EpService;
import com.example.fc.ep.epVo.EpVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String epLogin(HttpSession session) {
        session.removeAttribute("passwordFind");
        session.removeAttribute("emailFind");
        return "/loginForm";
    }

    @PostMapping("/epLogin")
    public String epLogin(EpVo epVo, HttpSession session){

        if (session.getAttribute("epLogin") != null){
            session.removeAttribute("epLogin");
        }
        EpVo vo = epService.epLogin(epVo);
        System.out.println("vo = " + vo);

        if (vo != null) {
            session.setAttribute("epLogin",vo);
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
    public String epModify(EpVo epVo, HttpSession session){
        epService.epModify(epVo);
        session.setAttribute("epLogin",epVo);
        System.out.println("업데이트 성공했습니다");
            return "/ex";
    }
    @PostMapping("/epDelete")
    public String epDelete(EpVo epVo, HttpSession session){
        int vo = epService.epDelete(epVo);
        System.out.println("지우기~!! 성공했습니다");
        session.removeAttribute("epLogin");

        return "/ex";
    }
    @ResponseBody // 값 변환을 위해 꼭 필요함
    @GetMapping("/idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int idCheck(EpVo epVo) throws Exception{
        System.out.println("epVo값 = " + epVo);
        int result = epService.idCheck(epVo); // 중복확인한 값을 int로 받음
        System.out.println("result +++++++++= 6+++++++++++++++++++::::" + result);
        return result;
    }
    @GetMapping("/epPassword")
    public String epPassword() {return "ep/epFindPass";
    }
    @PostMapping("/passwordFind")
    public String passwordFind(EpVo epVo, HttpSession session){
        EpVo vo = epService.epPasswordCheck(epVo);
        System.out.println("vo = " + vo);

        if (vo != null) {
            session.setAttribute("passwordFind",vo);
            System.out.println("비밀번호 는 ============"+vo);
            return "/ep/epFindResult";
        }else{
            System.out.println("요청하는 회원의(비밀번호찾기)정보가 없습니다.");
            return "/loginForm";
        }


    }
    @PostMapping("/emailFind")
    public String emailFind(EpVo epVo, HttpSession session){
        EpVo vo = epService.epEmailCheck(epVo);
        System.out.println("vo = " + vo);

        if (vo != null) {
            session.setAttribute("emailFind",vo);
            System.out.println("이메일은 는 ============"+vo);
            return "/ep/epFindResult";
        }else{
            System.out.println("요청하는 회원의 정보(이메일찾기)가 없습니다.");
            return "/loginForm";
        }

    }
}