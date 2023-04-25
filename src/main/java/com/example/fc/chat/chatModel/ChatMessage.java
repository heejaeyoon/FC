package com.example.fc.chat.chatModel;

import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private MemberVo memberVo;
    private EpVo epVo;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }



}