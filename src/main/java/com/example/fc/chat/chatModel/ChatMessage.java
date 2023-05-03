package com.example.fc.chat.chatModel;

import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private MessageType type;
    
    //채팅 내용
    private String content;
    
    // 채팅 보낸이
    private String sender;
    
    //객체로 정보 받아오기
    private MemberVo memberVo;
    private EpVo epVo;

    //회원 구분역할=>ep,member
    private String section;

    //방 번호 역할
    private int memberRoom;
    private int epRoom;

    //운영자와 대화 참여자 구분
    private String role;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }



}