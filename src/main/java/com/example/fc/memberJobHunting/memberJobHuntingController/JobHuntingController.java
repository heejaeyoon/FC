package com.example.fc.memberJobHunting.memberJobHuntingController;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class JobHuntingController {
    public final MemberJobHuntingService jobHunting;


    /*구직 게시글 부분*/
    @GetMapping("/memberJobHunting")
    public String getJobHunting() {

        return "jobHunting/memberJobHunting";
    }

    //구직게시판 저장
    @PostMapping("/memberJobHuntingForm")
    public String postJobHuntingForm(MemberJobHuntingVo jobHunt, MultipartFile[] file) throws IOException {
        System.out.println("uploadResult = " + file[0].getResource());
        System.out.println("jobHunt = " + jobHunt);
        int uploadResult = jobHunting.insertJobHunting(jobHunt, file);
        System.out.println("컨트롤러 게시판 저장 result = " + uploadResult);

        if (uploadResult == 1) {
            return "redirect:/memberJobHuntingList"; // redirect는 웹 페이지 경로 웹 페이지를 보여줌0
        }

        return "jobHunting/memberJobHuntingList"; // return는 html경로 => 순수 html만 보여줘서 오류
    }

    //게시판 리스트 : 페이징 처리
/*    @GetMapping("/memberJobHuntingList")
    public String getMemberJobHuntingList(Model model){
        int limit = 6; // 보여줄 게시글 갯수
        int offset = 0; //처음 초기 페이지 값
        List<MemberJobHuntingVo> jobHuntingList = jobHunting.findAllJobHunting(limit, offset);
        System.out.println("jobHuntingList = " + jobHuntingList);
        
        model.addAttribute("list", jobHuntingList);
        return "member/memberJobHuntingList";
    }*/

    //게시글 리스트
    @GetMapping("/memberJobHuntingList")
    public String getMemberJobHuntingList(Model model, @PageableDefault(page = 0,size = 6) Pageable pageable){
        List<MemberJobHuntingVo> jobHuntingList = jobHunting.findAllJobHunting();
        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(),jobHuntingList.size());
        final Page<MemberJobHuntingVo> page = new PageImpl<>(jobHuntingList.subList(start, end), pageable, jobHuntingList.size());
        System.out.println("jobHuntingList = " + jobHuntingList);

        model.addAttribute("list", page);
      /*  model.addAttribute("list", jobHuntingList);*/
        return "jobHunting/memberJobHuntingList";
    }
    

    //작성된 게시글 보기
    @GetMapping("/memberJobHuntingPoster")
    public String getMemberJobHuntingPoster(MemberJobHuntingVo memberBoard, Model model){
        MemberJobHuntingVo writerInfo = jobHunting.findAllByMemberBoard(memberBoard); //게시글 번호로 모든 정보 찾기
        System.out.println("writerInfo = " + writerInfo);
        List<MemberJobHuntingFilesVo> writerFilesInfo = jobHunting.findAllFilesByMemberBoard(memberBoard.getMemberBoard());//작성자가 등록한 이미지 모두 불러오기

        model.addAttribute("writerInfo",writerInfo); // 찾아온 모든 정보 모델에 삽입
        model.addAttribute("writerFilesInfo", writerFilesInfo);

        return "jobHunting/memberJobHuntingPoster";
    }


}
