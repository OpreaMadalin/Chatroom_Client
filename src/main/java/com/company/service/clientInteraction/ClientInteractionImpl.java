package com.company.service.clientInteraction;

import com.company.service.authService.AuthService;
import com.company.service.authService.AuthServiceImpl;
import com.company.service.chatroomService.ChatroomService;
import com.company.service.chatroomService.ChatroomServiceImpl;
import com.company.service.exception.InvalidClientInteractionException;

import java.util.Scanner;

import static com.company.util.Constants.*;

public class ClientInteractionImpl implements ClientInteractionService {

    private final Scanner scanner = new Scanner(System.in);

    AuthService authService = new AuthServiceImpl();
    ChatroomService chatroomService = new ChatroomServiceImpl();

    @Override
    public void initInteraction() {

        switch (chooseInitialAction()) {
            case REGISTER:
                authService.register();
                break;

            case LOGIN:
                authService.login();
                break;

            case CHATROOMS_MANAGEMENT:
                switch (choseChatroomsAction()) {
                    case ADD_CHATROOM:
                        chatroomService.addChatroom();
                        break;
                    case LIST_CHATROOMS:
                        chatroomService.getChatrooms();
                        break;
                    case UPDATE_CHATROOM:
                        System.out.println("Update chatroom");
                        break;
                    case DELETE_CHATROOM:
                        chatroomService.deleteChatroom();
                        break;
                    case LOGOUT:
                        initInteraction();
                        break;
                }
                break;
        }
        initInteraction();
    }

    private Integer choseChatroomsAction() {
        System.out.println("Add chatroom - press " + ADD_CHATROOM);
        System.out.println("List chatroom - press " + LIST_CHATROOMS);
        System.out.println("Update chatroom - press " + UPDATE_CHATROOM);
        System.out.println("Delete chatroom - press " + DELETE_CHATROOM);
        System.out.println("For back - press " + LOGOUT);

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != ADD_CHATROOM && action != LIST_CHATROOMS && action != UPDATE_CHATROOM &&
                    action != DELETE_CHATROOM && action != LOGOUT) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Please enter a valid number for your action!");
        }
        return choseChatroomsAction();
    }


    private Integer chooseInitialAction() {

        System.out.println("Choose action: ");
        System.out.println("Register - press " + REGISTER);
        System.out.println("Login - press " + LOGIN);
        System.out.println("Chatrooms - press " + CHATROOMS_MANAGEMENT);

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != REGISTER && action != LOGIN && action != CHATROOMS_MANAGEMENT) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Please enter a valid number: " + REGISTER + " (REGISTER) " +
                    " or " + LOGIN + " (LOGIN) " + " or " + CHATROOMS_MANAGEMENT + " (CHATROOMS)!");
        }
        return chooseInitialAction();
    }
}
