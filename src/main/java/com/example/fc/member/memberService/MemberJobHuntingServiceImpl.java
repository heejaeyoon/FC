package com.example.fc.member.memberService;

import com.example.fc.member.memberDao.MemberJobHuntingDao;
import com.example.fc.member.memberVo.MemberJobHuntingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberJobHuntingServiceImpl {

    private final MemberJobHuntingDao jobHuntingDao;

    //구직게시글 insert기능, 타임스탬프 이용함으로써 게시글 저장 날짜 자동 생성(now()사용 x)
    public int insertJobHunting(MemberJobHuntingVo jobHunt) {

        int result = jobHuntingDao.insertJobHunting(jobHunt);
        System.out.println("서비스 게시판 저장 result = " + result);
        return result;
    }

    public int insertMemberStack(MemberJobHuntingVo jobHunt) {
        //구직 게시글에 한 컬럼에 받아온 여러 기술 값을 배열에 하나씩 삽입
        String[] stack = jobHunt.getStack().split(" ");
        HashMap map = new HashMap();

        //게시글 정보로 member_board 값을 찾음 => member_stack에 값을 넣기 위해
        int member_board = jobHuntingDao.findJobHuntingId(jobHunt);
        String string_member_board = String.valueOf(member_board) ;

        //찾아온 member_board로 member_stack값에 저장
        for(int i=0; i<stack.length; i++){
            map.put("stack",stack[i]);
            map.put("string_member_board", string_member_board);
            int insertMemberStack = jobHuntingDao.insertMemberStack(map);
        }
        return 1;
    }
}
