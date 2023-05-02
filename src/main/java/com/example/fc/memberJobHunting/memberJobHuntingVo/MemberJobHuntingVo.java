package com.example.fc.memberJobHunting.memberJobHuntingVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class MemberJobHuntingVo {
    private Long memberBoard;
    private String title;
    private String stack;
    private String period;
    private String payment;
    private String workDay;
    private String addr;
    private String detail;
    private Date createDate;
    private String memberId;
    private String applicationPeriod;
    private Date updateDate;
    private String hunting;
    private int fileAttached; //파일 첨부상태 : 0 미첨부, 1 첨부



}
