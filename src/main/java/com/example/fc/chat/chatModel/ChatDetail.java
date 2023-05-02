package com.example.fc.chat.chatModel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatDetail {
    private Long id;
    private int ChatRoom;
    private String role;
    private String sender;
    private String chatDetail;
    private Date chatTime;
}
