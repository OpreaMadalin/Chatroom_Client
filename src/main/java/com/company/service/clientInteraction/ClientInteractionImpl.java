package com.company.service.clientInteraction;

import com.company.exceptions.InvalidClientInteractionException;
import com.company.service.chatroomService.ChatroomService;
import com.company.service.chatroomService.ChatroomServiceImpl;
import com.company.service.messageService.MessageService;
import com.company.service.messageService.MessageServiceImpl;

import java.util.Scanner;

import static com.company.util.constants.Chatroom.*;

public class ClientInteractionImpl implements ClientInteractionService {

    private final Scanner scanner = new Scanner(System.in);
    ChatroomService chatroomService = new ChatroomServiceImpl();
    MessageService messageService = new MessageServiceImpl();

    @Override
    public void initInteraction() {

        switch (choseChatroomsAction()) {
            case ADD_CHATROOM:
                chatroomService.addChatroom();
                break;

            case LIST_CHATROOMS:
                chatroomService.getChatrooms();
                break;

            case DELETE_CHATROOM:
                chatroomService.deleteChatroom();
                break;

            case UPDATE_CHATROOM_NAME:
                chatroomService.updateChatroomName();
                break;

            case UPDATE_CHATROOM_PASSWORD:
                chatroomService.updateChatroomPassword();
                break;

            case VIEW_MESSAGES:
                messageService.showChatroomMessages();
                break;

            case ADD_MESSAGE:
                messageService.addChatroomMessage();
                break;

            case ADD_ADMIN:
                chatroomService.addAdmin();
                break;

            case BAN_USER:
                chatroomService.addBannedUser();
                break;

            case LOGOUT:
                ClientRegisterLoginImpl registerLogin = new ClientRegisterLoginImpl();
                registerLogin.initInteraction();
                break;
        }

        initInteraction();
    }

    private Integer choseChatroomsAction() {

        System.out.println("-------- CHATROOM MANAGEMENT -------");
        System.out.println("Add chatroom - press " + ADD_CHATROOM);
        System.out.println("List chatroom - press " + LIST_CHATROOMS);
        System.out.println("Delete chatroom - press " + DELETE_CHATROOM);
        System.out.println("Update chatroom name - press " + UPDATE_CHATROOM_NAME);
        System.out.println("Update chatroom password - press " + UPDATE_CHATROOM_PASSWORD);
        System.out.println("-------- MESSAGE MANAGEMENT --------");
        System.out.println("Add chatroom message - press " + ADD_MESSAGE);
        System.out.println("Show chatroom messages - press " + VIEW_MESSAGES);
        System.out.println("-------- USERS MANAGEMENT --------");
        System.out.println("Add chatroom admin - press " + ADD_ADMIN);
        System.out.println("Add chatroom banned user - press " + BAN_USER);
        System.out.println("-------------- LOGOUT --------------");
        System.out.println("For logout - press " + LOGOUT);
        System.out.println("------------------------------------");

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != ADD_CHATROOM && action != LIST_CHATROOMS && action != UPDATE_CHATROOM_NAME &&
                    action != UPDATE_CHATROOM_PASSWORD && action != DELETE_CHATROOM && action != ADD_MESSAGE &&
                    action != VIEW_MESSAGES && action != ADD_ADMIN && action != BAN_USER && action != LOGOUT) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Please enter a valid number for your action!");
        }
        return choseChatroomsAction();
    }

}
