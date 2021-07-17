package com.company;

import com.company.service.clientInteraction.ClientInteractionImpl;
import com.company.service.clientInteraction.ClientInteractionService;
import com.company.service.clientInteraction.ClientRegisterLoginImpl;


public class Main {

    public static void main(String[] args) {

        ClientRegisterLoginImpl registerLogin = new ClientRegisterLoginImpl();
        registerLogin.initInteraction();

        ClientInteractionService clientInteractionService = new ClientInteractionImpl();
        clientInteractionService.initInteraction();

    }
}
