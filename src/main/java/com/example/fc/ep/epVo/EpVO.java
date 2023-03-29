package com.example.fc.ep.epVo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EpVO {
    private Long epBoard;
    private String title;
    private String stack;
    private String period;
    private String recruit;
    private String gender;
    private String payment;
    private String workDayStart;
    private String workDayEnd;
    private String addr;
    private String detail;
    private String createDate;
    private String epName;
    private String updateDate;
}
