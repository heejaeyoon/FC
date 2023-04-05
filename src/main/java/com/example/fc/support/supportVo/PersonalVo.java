package com.example.fc.support.supportVo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalVo {

    private Long id;
    private String title;
    private String detail;
    private String email;
    private String role;
    private int ing;
    private String updateDate;
    private String createDate;

}