package com.example.fc.chat.chatDao;

import com.example.fc.chat.chatModel.ChatDetail;
import com.example.fc.chat.chatModel.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ChatDao {

    /*일반회원*/
    //일반회원 방 존재 유무 판단
    Optional<Integer> findMemberRoomById(int memberRoom);

    //방 생성
    int createMemberRoom(int memberRoom);

    //채팅 삽입
    int insertMemberMsg(ChatMessage chatMessage);
    
    //채팅 내역 불러오기
    List<ChatDetail> findMemberChatHistory(int memberRoom);


    /*기업회원*/ 
    //기업회원 방 존재 유무 판단
    Optional<Integer> findEpRoomById(int epRoom);
    
    //방 생성 
    int createEpRoom(int epRoom);

    //채팅 저장
    int insertEpMsg(ChatMessage chatMessage);

    //채팅 내역 불러오기
    List<ChatDetail> findEpChatHistory(HashMap map);

    //모든 멤버 채팅 불러오기
    List<ChatDetail> findAllMemberChat();

    List<ChatDetail> findAllEpChat();
}
