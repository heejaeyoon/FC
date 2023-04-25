package com.example.fc.chat.chatService;

import com.example.fc.chat.chatDao.ChatRoomDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomDao chatDao;

    //채팅방 생성
    public void insertChatRoom(String sender) {
       int result = chatDao.insertChatRoom(sender);

    }
}
