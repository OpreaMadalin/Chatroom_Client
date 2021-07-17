package com.company.service.authService;

import java.util.ArrayList;

public class TokenService {

    public static String getToken() {

        String token = AuthServiceImpl.getToken();
        ArrayList<String> tokens = new ArrayList();
        tokens.add(token);

        for (String str : tokens) {
            return str;
        }
        return null;
    }

}
