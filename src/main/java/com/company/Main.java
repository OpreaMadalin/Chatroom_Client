package com.company;

import com.company.service.clientInteraction.ClientInteractionImpl;
import com.company.service.clientInteraction.ClientInteractionService;


public class Main {

    public static void main(String[] args) {

        ClientInteractionService clientInteractionService = new ClientInteractionImpl();
        clientInteractionService.initInteraction();

    }
}
