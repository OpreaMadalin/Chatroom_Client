package com.company.service.clientInteraction;

import com.company.service.authService.AuthService;
import com.company.service.authService.AuthServiceImpl;
import com.company.service.exception.InvalidClientInteractionException;

import java.util.Scanner;

import static com.company.util.Constants.LOGIN;
import static com.company.util.Constants.REGISTER;

public class ClientRegisterLoginImpl implements ClientInteractionService {

    Scanner scanner = new Scanner(System.in);
    AuthService authService = new AuthServiceImpl();

    @Override
    public void initInteraction() {

        switch (chooseInitialAction()) {
            case LOGIN:
                authService.login();
                break;

            case REGISTER:
                authService.register();
                initInteraction();
                break;
        }
    }

    private Integer chooseInitialAction() {

        System.out.println("--- MADALIN CHATROOM ---");
        System.out.println("Choose action: ");
        System.out.println("Login - press " + LOGIN);
        System.out.println("Register - press " + REGISTER);
        System.out.println("------------------------");

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != LOGIN && action != REGISTER) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Please enter a valid number: " + LOGIN + " (LOGIN) " +
                    " or " + REGISTER + " (REGISTER) ");
        }
        return chooseInitialAction();
    }


}
