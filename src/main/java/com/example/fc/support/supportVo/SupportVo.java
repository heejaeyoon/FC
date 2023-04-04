package com.example.fc.support.supportVo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupportVo {

    private Long noticeBoard;
    private String title;
    private String detail;
    private String sortation;
    private String createDate;


}