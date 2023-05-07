package com.example.fc.chat.chatService;

import com.example.fc.chat.chatDao.ChatDao;
import com.example.fc.chat.chatModel.ChatDetail;
import com.example.fc.chat.chatModel.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {


  private final ChatDao chatDao;
  
  /*member 관련*/
  //방 존재 유무
  public int findMemberRoomById(int memberRoom) {
    Optional<Integer> result = chatDao.findMemberRoomById(memberRoom);
    if (result.isEmpty()) {
        return 0;
    }
    return result.get();
  }

  //방 생성
  public int createMemberRoom(int memberRoom) {
    int result = chatDao.createMemberRoom(memberRoom);

    return result;
  }

  //채팅 저장
  public int insertMemberMsg(ChatMessage chatMessage) {
    int result = chatDao.insertMemberMsg(chatMessage);

    return result;
  }
  
  //채팅 내역 불러오기
  public List<ChatDetail> findMemberChatHistory(int memberRoom) {
    List<ChatDetail> result = chatDao.findMemberChatHistory(memberRoom);

    return result;
  }

  /*ep관련*/
  //방 존재 유무 판단
  public int findEpRoomById(int epRoom) {
    Optional<Integer> result = chatDao.findEpRoomById(epRoom);
    if (result.isEmpty()) {
      return 0;
    }
    return result.get();
  }

  //방 생성
  public int createEpRoom(int epRoom) {
    int result = chatDao.createEpRoom(epRoom);

    return result;
  }

  //채팅 저장
  public int insertEpMsg(ChatMessage chatMessage) {
    int result = chatDao.insertEpMsg(chatMessage);

    return result;
  }

  //채팅 내역 불러오기
  public List<ChatDetail> findEpChatHistory(HashMap map) {
    List<ChatDetail> result = chatDao.findEpChatHistory(map);

    return result;
  }

  //모든 개인회원 채팅방 불러오기
  public List<ChatDetail> findAllMemberChat() {
    List<ChatDetail> list = chatDao.findAllMemberChat();
    return list;
  }
  
  //모든 기업회원 채팅방 불러오기
  public List<ChatDetail> findAllEpChat(){
    List<ChatDetail> list = chatDao.findAllEpChat();
    return list;
  }
}