
package com.example.fc.ep.epVo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpVo {
    private Long ep_id;
    private String name;
    private String content;
    private String password;
    private String addr;
    private String phone;
    private String bn_number;
    private String email;
    private String role;

}