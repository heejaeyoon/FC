package com.example.fc.memberJobHunting.memberJobHuntingController;

import com.example.fc.member.memberVo.MemberVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingFilesVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingVo;
import com.example.fc.memberJobHunting.memberJobHuntingservice.MemberJobHuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/jobHunting")
@Controller
public class JobHuntingController {
    public final MemberJobHuntingService jobHunting;


    /*구직 게시글 부분*/
    @GetMapping("/jobHuntingForm")
    public String getJobHunting() {

        return "jobHunting/memberJobHuntingForm";
    }

    //구직게시판 저장
    @PostMapping("/jobHuntingForm")
    public String postJobHuntingForm(MemberJobHuntingVo jobHunt, MultipartFile[] file, String showingDate, String showingHour, String showingMin, HttpSession session) throws IOException {
        System.out.println("uploadResult = " + file[0].getResource());
        System.out.println("jobHunt = " + jobHunt);
        String showingPeriod = showingDate + " " + showingHour + ":" + showingMin + ":59"; //yyyy-mm-dd hh:mm:ss 으로 저장
        MemberVo userInfo = (MemberVo)session.getAttribute("memberLogin");
        jobHunt.setMemberId(userInfo.getId());
        jobHunt.setShowingPeriod(showingPeriod);
        int uploadResult = jobHunting.insertJobHunting(jobHunt, file);
        System.out.println("컨트롤러 게시판 저장 result = " + uploadResult);

        if (uploadResult == 1) {
            return "redirect:/jobHunting/jobHuntingList"; // redirect는 웹 페이지 경로 웹 페이지를 보여줌0
        }

        return "jobHunting/memberJobHuntingList"; // return는 html경로 => 순수 html만 보여줘서 오류
    }

    //게시글 리스트
    @GetMapping("/jobHuntingList")
    public String getMemberJobHuntingList(Model model, HttpSession session, @PageableDefault(page = 0, size = 6) Pageable pageable) {
        if (session.getAttribute("epLogin") != null || session.getAttribute("memberLogin") != null) {
            List<MemberJobHuntingVo> jobHuntingList = jobHunting.findAllJobHunting();
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), jobHuntingList.size());
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
        MemberJobHuntingVo writerInfo = jobHunting.findAllByMemberBoard(memberBoard); //게시글 번호로 모든 정보 찾기
        System.out.println("writerInfo = " + writerInfo);
        List<MemberJobHuntingFilesVo> writerFilesInfo = jobHunting.findAllFilesByMemberBoard(memberBoard.getMemberBoard());//작성자가 등록한 이미지 모두 불러오기

        model.addAttribute("writerInfo", writerInfo); // 찾아온 모든 정보 모델에 삽입
        model.addAttribute("writerFilesInfo", writerFilesInfo);

        return "jobHunting/memberJobHuntingPoster";
    }


}
