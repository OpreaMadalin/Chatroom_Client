package com.company.service.chatroomService;

public interface ChatroomService {

    void addChatroom();

    void getChatrooms();

    void deleteChatroom();

    void updateChatroomName();

    void updateChatroomPassword();

    void addAdmin();

    void addBannedUser();
}
