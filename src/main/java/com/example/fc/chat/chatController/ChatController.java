package com.example.fc.chat.chatController;




import com.example.fc.chat.chatModel.ChatDetail;
import com.example.fc.chat.chatModel.ChatMessage;
import com.example.fc.chat.chatService.ChatService;
import com.example.fc.chat.chatVo.ChatRoomVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    //@SendTo() 이용안하면 사용하는 변수
    // convertAndSend 는 객체를 인자로 넘겨주면 자동으로 Message 객체로 변환 후 도착지로 전송한다.
   private final SimpMessageSendingOperations sendingOperations;
    public  final SimpMessagingTemplate template;

    /* **************** 참고용 소스 ************ */
    @GetMapping("/chat")
    public String getChat(){

        return "chat/view/chat";
    }

    @GetMapping("/topic/public/{roomId}")
    public void aaa(@PathVariable("roomId") String roomId) {
        System.out.println("roomId~~ = " + roomId);

    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("dudlrkdkseoa?");
        System.out.println(" ct sendMessage getContent= " + chatMessage.getContent());
        System.out.println(" ct sendMessage type= " + chatMessage.getType());
        return chatMessage;
    }

    @MessageMapping("/chat.addUse")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @PathVariable("roomId") String roomId) {
        //@Payload는 메세지에서 실제로 전송하고자 하는 데이터를 말하는듯
        System.out.println("chatMessage aaa = " + chatMessage);
        //일반회원 로그인시 chatMessage 안 memberVo객체에 값을 저장
        System.out.println("nickName = " + roomId);

        // Add username in web socket session
        System.out.println("여기서 된디?");
        headerAccessor.getSessionAttributes().put("roomId", chatMessage.getMemberVo().getId());
        headerAccessor.getSessionAttributes().put("username",chatMessage.getMemberVo().getNickName());
        System.out.println("여기서 된디??");
        System.out.println("ct addUser  getSender= " + chatMessage.getMemberVo().getNickName());
        template.convertAndSend("/sub/chat/room/"+chatMessage.getMemberVo().getId(),chatMessage);
        System.out.println("headerAccessor = " + headerAccessor);

        return chatMessage;
    }
    /* ************************************************ */

    /* 소스코드 */

    //관리자용 모든 채팅방 보기
    @GetMapping("/chatRoom")
    public String adminRoom(Model model){
        //모든 채팅 불러오기
        List<ChatDetail> allMemberChatList = chatService.findAllMemberChat();
        System.out.println("allMemberChatList = " + allMemberChatList.size());
        if(allMemberChatList.size()==0){
            model.addAttribute("memberList","문의 내용이 없습니다.");
        }
        List<ChatDetail> allEpChatList = chatService.findAllEpChat();
        System.out.println("allEpChatList = " + allEpChatList);
        if(allEpChatList.size() == 0){
            model.addAttribute("epList", "문의 내용이 없습니다.");
        }

        model.addAttribute("memberList", allMemberChatList);
        model.addAttribute("epList",allEpChatList);

        return "chat/view/chatRoom";
    }

    @GetMapping("/memberChatRoom/{memberRoom}")
    public String getMemberChat(@PathVariable("memberRoom") String memberRoom){

        return "chat/view/memberChat";
    }

    @GetMapping("/epChatRoom/{epRoom}")
    public String getEpChatMonitor(@PathVariable("epRoom") String epRoom ){

        return "chat/view/epChat";
    }


    //일반회원용 채팅
    @GetMapping("/memberChat/enterMemberRoom/{memberRoom}")
    public String getMemberChat(@PathVariable("memberRoom") int memberRoom, Model model){
        System.out.println("memberRoom = " + memberRoom);
        //방 존재 유무
        int result = chatService.findMemberRoomById(memberRoom);
        if (result == 0) {
            //방 생성
           int createRoom = chatService.createMemberRoom(memberRoom);
            System.out.println("채팅방 생성 결과 = " + createRoom);
        } else if (result>0) {
            //대회 기록 불러오기
            System.out.println("여기에 들어오긴해?");
            List<ChatDetail> chatHistory = chatService.findMemberChatHistory(memberRoom);
            chatHistory.forEach((i)-> System.out.println("채팅 내역 = " + i) );
            model.addAttribute("chatHistory", chatHistory);
        }
        return "chat/view/memberChat";
    }

    //기업회원용 채팅
    @GetMapping("/epChat/enterEpRoom/{epRoom}")
    public String getEpChat(@PathVariable("epRoom") int epRoom, Model model){
        System.out.println("epRoom = " + epRoom);
        //방 존재 유무
        int result = chatService.findEpRoomById(epRoom);
        System.out.println("result 기업 회원 방개수= " + result);
        if (result == 0) {
            //방 생성
            int createRoom = chatService.createEpRoom(epRoom);
            System.out.println("채팅방 생성 결과 = " + createRoom);
        } else if (result == 1) {
            //대회 기록 불러오기
            System.out.println("여기에 들어오긴해?");
            HashMap map = new HashMap();
            map.put("section", "ep");
            map.put("epRoom",epRoom);
            List<ChatDetail> chatHistory = chatService.findEpChatHistory(map);
            chatHistory.forEach((i)-> System.out.println("채팅 내역 = " + i) );
            model.addAttribute("chatHistory", chatHistory);
        }

        return "chat/view/epChat";
    }

    //메세지 맵핑 관련
    @MessageMapping("/chat/memberMsg")
    public void userChatMapping(@Payload ChatMessage chatMessage){
        System.out.println("chatMessage = " + chatMessage);

       //받아온 메세지를 db에 저장
        int insertMsg = chatService.insertMemberMsg(chatMessage);

        //db에 메세지 삽입 성공시 메세지 보내기
        if(insertMsg == 1){
            template.convertAndSend("/topic/memberRoom/"+chatMessage.getMemberVo().getId(), chatMessage);
        }
    }

    @MessageMapping("/chat/epMsg")
    public void epChatMapping(@Payload ChatMessage chatMessage, String epRoom){
        System.out.println("chatMessage = " + chatMessage);
        System.out.println("epRoom = " + epRoom);
        //받아온 메세지를 db에 저장
        int insertMsg = chatService.insertEpMsg(chatMessage);

        //db에 메세지 삽입 성공시 메세지 보내기
        if(insertMsg == 1){

            template.convertAndSend("/topic/epRoom/"+ chatMessage.getEpRoom(), chatMessage);
        }

    }

}