package com.example.fc.support.supportVo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class NoticeVo {

    private Long id;
    private String title;
    private String detail;
    private String email;
    private int ing;
    private String updateDate;
    private String createDate;
    private String categories;
    private int fileAttached; //파일 첨부상태 : 0 미첨부, 1 첨부



}