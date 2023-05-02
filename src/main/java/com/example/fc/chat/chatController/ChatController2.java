package com.example.fc.chat.chatController;

import com.example.fc.chat.chatModel.MessageModel;
import com.example.fc.chat.chatStorage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController2 {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}") //config에서 endPoint에서 설정한 주소를 입력
    public void sendMessage(@DestinationVariable String to, MessageModel message){
        System.out.println("to = " + to + ", handling send message = " + message);
        boolean isExist = UserStorage.getInstance().getUsers().contains(to);
        if (isExist) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }

    }


}
