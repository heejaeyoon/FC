package com.example.fc.chat.chatDao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRoomDao {

    public int insertChatRoom(String sender);


}
