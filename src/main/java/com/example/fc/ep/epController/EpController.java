package com.example.fc.ep.epController;

import com.example.fc.ep.epService.EpService;
import com.example.fc.ep.epVo.EpVo;
import com.example.fc.support.supportService.EpOneToOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@Slf4j
public class EpController {

    @Autowired
    EpService epService;
    @Autowired
    EpOneToOneService epOneToOneService;


    /* 회원가입 */
    @GetMapping("/epInsert")
    public String epJoin(){
        return "/ep/enterpriseJoinForm";
    }
    
    //회원가입 할 데이터 전송
    @PostMapping("/epInsert")
    @ResponseBody // 값 변환을 위해 꼭 필요함
    public String memberJoin(EpVo epVo){
        log.info("회원가입 폼에서 입력받은 데이터: {}",epVo);
        epService.epJoin(epVo);
        return "complete";
    }

    /* 로그인 */
    @GetMapping("/epLogin")
    public String epLogin(HttpSession session) {
        session.removeAttribute("passwordFind");
        session.removeAttribute("emailFind");
        return "/loginForm";
    }
    @GetMapping("/addMoreGetJob")
    public String addMoreGetJob(){

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
            //redirect: / 변수명 을 줘야 다시 창을 불러올수있음
            return "redirect:/";
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
    public String epModify(EpVo epVo, HttpSession session, @RequestParam String zipNo, @RequestParam String wRestAddress){
        String totalAddr = epVo.getAddr() + "|" +zipNo +"|"+ wRestAddress;
        System.out.println("totalAddr = " + totalAddr);
        epVo.setAddr(totalAddr);
        epService.epModify(epVo);
        session.setAttribute("epLogin",epVo);
        System.out.println("업데이트 성공했습니다");
            return "main";
    }
    @PostMapping("/epDelete")
    public String epDelete(EpVo epVo, HttpSession session){
        epService.epDelete(epVo);
        session.removeAttribute("epLogin");
        return "main";
    }
    @ResponseBody // 값 변환을 위해 꼭 필요함
    @GetMapping("/idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int idCheck(EpVo epVo) throws Exception{
        System.out.println("epVo값 = " + epVo);
        int result = epService.idCheck(epVo); // 중복확인한 값을 int로 받음
        System.out.println("result +++++++++= 6+++++++++++++++++++::::" + result);
        return result;
    }

    @ResponseBody // 값 변환을 위해 꼭 필요함
    @GetMapping("/nameCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int nameCheck(EpVo  epVo) throws Exception{
        System.out.println("epVo값 = " + epVo);
        int result = epService.nameCheck(epVo); // 중복확인한 값을 int로 받음
        System.out.println("result +++++++++= 6+++++++++++++++++++::::" + result);
        return result;
    }

    @GetMapping("/epPassword")
    public String epPassword() {return "ep/epFindPass";
    }

    @GetMapping("/epFindResult")
    public String epFindResult() {return "epFindResult";
    }

    @ResponseBody
    @GetMapping("/epFindAlert")
    public String epFindAlert(){
        String failmessage ="";
        failmessage = "<script>alert('올바르지 않은 정보입니다.'); history.go(-1);</script>";
        return failmessage;
    }


    @PostMapping("/passwordFind")
    public String passwordFind(EpVo epVo, HttpSession session){
        EpVo vo = epService.epPasswordCheck(epVo);
        System.out.println("vo = " + vo);
//        alert을 하기위해서 미리 메세지를 함수로 설정해준다 // 리스폰스바디를 해줘야한다.
        String failmessage ="";
        if (vo != null) {
            session.setAttribute("passwordFind",vo);
            System.out.println("비밀번호 는 ============"+vo);
            return "/ep/epFindResult";
        }
            return "redirect:/epFindAlert";
    }

    @PostMapping("/emailFind")
    public String emailFind(EpVo epVo, HttpSession session){
        EpVo vo = epService.epEmailCheck(epVo);
        System.out.println("vo = " + vo);
        //        alert을 하기위해서 미리 메세지를 함수로 설정해준다 // 리스폰스바디를 해줘야한다.
        String failmessage ="";
        if (vo != null) {
            session.setAttribute("emailFind",vo);
            System.out.println("이메일은 는 ============"+vo);
            return "/ep/epFindResult";
        }return "redirect:/epFindAlert";
    }


//    @GetMapping("/epPage")
//    public String myPage(EpOneToOneVo epOneToOneVo, HttpSession session,Model model) {
//        session.setAttribute("epOneToOneVo", epOneToOneVo);
//        int count = epOneToOneService.epOneToOneCount(epOneToOneVo);
//        System.out.println("epOneToOneVo = " + epOneToOneVo);
//        model.addAttribute("count",count);
//        System.out.println("count = " + count);
//        session.getAttribute("epLogin");
//        System.out.println("세션값 -----------------" + session.getAttribute("epLogin"));
//        return "/ep/epMyPageForm";
//    }
//@GetMapping("/epPage")
//public String myPage(HttpSession session, Model model) {
//    EpOneToOneVo epOneToOneVo = (EpOneToOneVo) session.getAttribute("epOneToOneVo");
//    if (epOneToOneVo == null) {
//        epOneToOneVo = new EpOneToOneVo();
//        epOneToOneVo.setEmail(session.getAttribute("epLogin").getEmail()); // email 속성 설정
//        session.setAttribute("epOneToOneVo", epOneToOneVo);
//    }
//    int count = epOneToOneService.epOneToOneCount(epOneToOneVo);
//    System.out.println("epOneToOneVo = " + epOneToOneVo);
//    model.addAttribute("count", count);
//    System.out.println("count = " + count);
//    session.getAttribute("epLogin");
//    System.out.println("세션값 -----------------" + session.getAttribute("epLogin"));
//    return "/ep/epMyPageForm";
//}

    //주소API컨트롤러


    @GetMapping("/addrSearch")

    public String addTest() {
        return "ep/addrSearch";
    }


}