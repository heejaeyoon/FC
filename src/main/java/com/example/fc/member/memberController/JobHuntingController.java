package com.example.fc.member.memberController;

import com.example.fc.member.memberService.MemberJobHuntingServiceImpl;
import com.example.fc.member.memberVo.MemberJobHuntingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class JobHuntingController {
    public final MemberJobHuntingServiceImpl jobHunting;


    /*구직 게시글 부분*/
    @GetMapping("/memberJobHunting")
    public String getJobHunting() {

        return "member/memberJobHunting";
    }

    //구직게시판 저장
    @ResponseBody // fetch결과값 스트링으로 전달
    @PostMapping("/memberJobHuntingForm")
    public String postJobHuntingForm(MemberJobHuntingVo jobHunt, MultipartFile[] file) throws IOException {
        System.out.println("uploadResult = " + file[0].getResource());
        System.out.println("jobHunt = " + jobHunt);
        int uploadResult = jobHunting.insertJobHunting(jobHunt, file);
        System.out.println("컨트롤러 게시판 저장 result = " + uploadResult);
     /*   if (uploadResult == 1) {
            int stackResutl = jobHunting.insertMemberStack(jobHunt);
            System.out.println("stackResutl = " + stackResutl);

            if (stackResutl == 1) {
                return "게시글이 작성되었습니다.";
            }

        } else if (uploadResult == 0) {
            return "게시글 작성 실패.";
        }*/

        return "데이터베이스";
    }

    //게시판 리스트
/*
    @GetMapping("/memberJobHuntingList")
    public String getMemberJobHuntingList(){


    }
*/


}
