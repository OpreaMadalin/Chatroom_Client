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
            case REGISTER:
                authService.register();
                initInteraction();
                break;
            case LOGIN:
                authService.login();
                break;
        }
    }

    private Integer chooseInitialAction() {

        System.out.println("Choose action: ");
        System.out.println("Register - press " + REGISTER);
        System.out.println("Login - press " + LOGIN);

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != REGISTER && action != LOGIN) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Please enter a valid number: " + REGISTER + " (REGISTER) " +
                    " or " + LOGIN + " (LOGIN) ");
        }
        return chooseInitialAction();
    }


}
