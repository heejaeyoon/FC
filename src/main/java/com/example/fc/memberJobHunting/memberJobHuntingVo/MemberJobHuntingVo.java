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
    private Long memberId;
    private String title;
    private String stack;
    private String showingPeriod;
    private String recruitCondition;
    private String gender;
    private String payment;
    private String workDayStart;
    private String workDayEnd;
    private String addr;
    private String detail;
    private String createDate;
    private String updateDate;
    private int fileAttached;

}
