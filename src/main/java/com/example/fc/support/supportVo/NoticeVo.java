package com.example.fc.support.supportVo;

import lombok.*;

<<<<<<<< HEAD:src/main/java/com/example/fc/support/supportVo/EpOneToOneVo.java

========
>>>>>>>> master:src/main/java/com/example/fc/support/supportVo/NoticeVo.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
<<<<<<<< HEAD:src/main/java/com/example/fc/support/supportVo/EpOneToOneVo.java
public class EpOneToOneVo {
========
public class NoticeVo {
>>>>>>>> master:src/main/java/com/example/fc/support/supportVo/NoticeVo.java

    private Long id;
    private String title;
    private String detail;
    private String email;
    private int ing;
    private String updateDate;
    private String createDate;
<<<<<<<< HEAD:src/main/java/com/example/fc/support/supportVo/EpOneToOneVo.java
    private String categories;
========
    private int fileAttached; //파일 첨부상태 : 0 미첨부, 1 첨부
>>>>>>>> master:src/main/java/com/example/fc/support/supportVo/NoticeVo.java


}