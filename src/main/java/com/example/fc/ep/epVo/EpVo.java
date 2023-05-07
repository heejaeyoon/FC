
package com.example.fc.ep.epVo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpVo {
    private Long epId;
    private String name;
    private String content;
    private String password;
    private String addr;
    private String phone;
    private String bnNumber;
    private String email;
    private String role;
    private int emailVerified; //email인증 0 미인증, 1인증
    private int hiddenValue; // 0 : email인증 0, 1 : 이메일인증 1

}