package com.example.fc.ep.epVo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EpVO {
    private Long ep_board;
    private String title;
    private String stack;
    private String period;
    private String recruit;
    private String gender;
    private String payment;
    private String work_day;
    private String addr;
    private String detail;
    private String create_date;
    private String ep_name;
    private String update_date;
}
