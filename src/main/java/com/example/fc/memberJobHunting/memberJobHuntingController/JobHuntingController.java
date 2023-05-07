package com.example.fc.memberJobHunting.memberJobHuntingController;

import com.example.fc.email.service.EmailSenderService;
import com.example.fc.ep.epVo.EpVo;
import com.example.fc.epRecruit.epRecruitService.EpRecruitService;
import com.example.fc.member.memberVo.MemberVo;
import com.example.fc.memberJobHunting.memberJobHuntingEmailDto.MemberJobHuntingEmailDto;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingFilesVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingStackVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingVo;
import com.example.fc.memberJobHunting.memberJobHuntingservice.MemberJobHuntingService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/jobHunting")
@Controller
public class JobHuntingController {


    public final MemberJobHuntingService jobHunting;

    //기업회원에게 이메일 보내기위함
    private final EpRecruitService epRecruitService;
    private final EmailSenderService emailSenderService;

    /*구직 게시글 부분*/
    @GetMapping("/jobHuntingForm")
    public String getJobHunting() {

        return "jobHunting/memberJobHuntingForm";
    }

    //구직게시판 저장
    @PostMapping("/jobHuntingForm")
    public String postJobHuntingForm(MemberJobHuntingVo jobHunt, MultipartFile[] file, String showingDate, String showingHour, String showingMin, HttpSession session) throws IOException {
        System.out.println("insertResult = " + file[0].getResource());
        System.out.println("jobHunt = " + jobHunt);
        String showingPeriod = showingDate + " " + showingHour + ":" + showingMin + ":59"; //yyyy-mm-dd hh:mm:ss 으로 저장
        MemberVo userInfo = (MemberVo) session.getAttribute("memberLogin");
        jobHunt.setMemberId(userInfo.getId());
        jobHunt.setShowingPeriod(showingPeriod);
        int insertResult = jobHunting.insertJobHunting(jobHunt, file);
        System.out.println("컨트롤러 게시판 저장 result = " + insertResult);

        if (insertResult == 1) {
            return "redirect:/jobHunting/jobHuntingList"; // redirect는 웹 페이지 경로 웹 페이지를 보여줌0
        }

        return "jobHunting/memberJobHuntingList"; // return는 html경로 => 순수 html만 보여줘서 오류
    }

    //구직 게시글의 파일 저장
    @PostMapping(value = "uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
        JsonObject insertResult = jobHunting.insertFile(multipartFile);

        return insertResult;
    }

    //게시글 리스트
    @GetMapping("/jobHuntingList")
    public String getMemberJobHuntingList(Model model, HttpSession session, @PageableDefault(page = 0, size = 6) Pageable pageable) {
        if (session.getAttribute("epLogin") != null || session.getAttribute("memberLogin") != null) {
            List<MemberJobHuntingVo> jobHuntingList = jobHunting.findAllJobHunting();
            
            //페이징처리
            //getOffset은 현제 페이지 넘버를 알려주는 함수
            //getPageSize() 는 화면에 보여줄 리스트 수
            int start = (int) pageable.getOffset(); //처음페이지
            int end = Math.min(start + pageable.getPageSize(), jobHuntingList.size()); //마지막 페이지
            final Page<MemberJobHuntingVo> page = new PageImpl<>(jobHuntingList.subList(start, end), pageable, jobHuntingList.size());
            System.out.println("jobHuntingList = " + jobHuntingList);

            model.addAttribute("list", page);
            /*  model.addAttribute("list", jobHuntingList);*/
            return "jobHunting/memberJobHuntingList";
        } else {
            return "redirect:/login";
        }
    }


    //작성된 게시글 보기
    @GetMapping("/jobHuntingPoster")
    public String getMemberJobHuntingPoster(MemberJobHuntingVo memberBoard, Model model) {
        MemberJobHuntingVo boardInfo = jobHunting.findAllByMemberBoard(memberBoard); //게시글 번호로 모든 정보 찾기
        MemberVo memberInfo = jobHunting.memberInfo(boardInfo.getMemberId());//게시글 쓴 사람 정보 다 가져오기
        System.out.println("boardInfo = " + boardInfo);
        List<MemberJobHuntingFilesVo> writerFilesInfo = jobHunting.findAllFilesByMemberBoard(memberBoard.getMemberBoard());//작성자가 등록한 이미지 모두 불러오기

        model.addAttribute("writerName", memberInfo);
        model.addAttribute("writerInfo", boardInfo); // 찾아온 모든 정보 모델에 삽입
        model.addAttribute("writerFilesInfo", writerFilesInfo);

        return "jobHunting/memberJobHuntingPoster";
    }

    //수정할 게시글 보기
    @GetMapping("/updateForm")
    public String update(Model model, MemberJobHuntingVo memberBoard) {
        System.out.println("memberBoard update= " + memberBoard);
        System.out.println("memberBoard id = " + memberBoard.getMemberBoard());
        MemberJobHuntingVo boardInfo = jobHunting.findAllByMemberBoard(memberBoard); //게시글 번호로 모든 정보 찾기
        List<MemberJobHuntingStackVo> stacks = jobHunting.findAllStacksByMemberBoard(memberBoard); // 스택들
        MemberVo writerInfo = jobHunting.memberInfo(boardInfo.getMemberId());//게시글 쓴 사람 정보 다 가져오기

        model.addAttribute("boardInfo", boardInfo);//게시글 모든 정보
        model.addAttribute("writerInfo", writerInfo); //이름

        return "jobHunting/memberJobHuntingUpdate";
    }

    //게시글 수정하기
    @PostMapping("/updateForm")
    public String postUpate(Model model, MemberJobHuntingVo memberBoard, String showingDate, String showingHour, String showingMin) {
        System.out.println("memberBoard = " + memberBoard);
        int result = jobHunting.updateJobHuntingBoard(memberBoard, showingDate, showingHour, showingMin);
        System.out.println("updateResult = " + result);
        if (result == 1) {
            return "redirect:/jobHunting/jobHuntingList";
        }
        return "redirect:/jobHunting/updateForm?memberBoard="+memberBoard.getMemberBoard(); //실패시 다시 돌아감
    }

    //게시글 삭제하기
    @GetMapping("/delete")
    public String delete(Long memberBoard) {
        int result = jobHunting.deleteMemberBoardByMemberBoard(memberBoard);
        System.out.println("게시글 삭제 결과는? = " + result);
        return "redirect:/jobHunting/jobHuntingList";
    }
    
    //지원서 양식
    @GetMapping("/sendEmail")
    public String getSendEmail(@RequestParam Long toSendAddr, Model model){
        EpVo writerInfo = epRecruitService.epInfo(toSendAddr);
        // MemberVo writerInfo = jobHunting.memberInfo(toSendAddr);

        model.addAttribute("writerInfo",writerInfo);
        return "jobHunting/memberJobHuntingEmailForm";
    }

    //이력서 보내기
    @PostMapping("/sendEmail")
    @ResponseBody
    public String PostSendEmail(MemberJobHuntingEmailDto dto){
        System.out.println("dto = " + dto);
        System.out.println("이프문 통과?");
        int send = emailSenderService.sendEmailToEp(dto);
        if (send==1) {
            return "이메일을 성공적으로 보냈습니다";
        }

        return "이메일 보내기 실패.";
    }

    

}
