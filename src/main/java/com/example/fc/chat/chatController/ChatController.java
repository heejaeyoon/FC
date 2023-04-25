package com.example.fc.chat.chatController;




import com.example.fc.chat.chatModel.ChatMessage;
import com.example.fc.chat.chatService.ChatService;
import com.example.fc.ep.epVo.EpVo;
import com.example.fc.member.memberVo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public String getChat(){

        return "chat/view/chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println(" ct sendMessage = " + chatMessage.getSender());
        System.out.println(" ct sendMessage getContent= " + chatMessage.getContent());
        System.out.println(" ct sendMessage type= " + chatMessage.getType());
        System.out.println(" ct sendMessage getSender= " + chatMessage.getSender());

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("chatMessage aaa = " + chatMessage);
        //일반회원 로그인시 chatMessage 안 memberVo객체에 값을 저장

 /*       if((boolean)session.getAttribute("memberLogin")) {
            MemberVo member = (MemberVo) session.getAttribute("memberLogin");
            chatMessage.setMemberVo(member);

            // Add username in web socket session
            headerAccessor.getSessionAttributes().put("username", chatMessage.getMemberVo());
        }
        //기업회원 로그인시 chatMeesage 안 EpVo객체에 값을 저장
        else if ((boolean)session.getAttribute("epLogin")) {
            EpVo ep = (EpVo) session.getAttribute("epLogin");
            chatMessage.setEpVo(ep);

            // Add username in web socket session
            headerAccessor.getSessionAttributes().put("username", chatMessage.getEpVo());
        }
*/

        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username",chatMessage.getMemberVo());
        System.out.println("ct addUser  getSender= " + chatMessage.getMemberVo());


     /*   chatService.insertChatRoom(chatMessage.getSender()); // 채팅방 생성*/
        return chatMessage;
    }

}