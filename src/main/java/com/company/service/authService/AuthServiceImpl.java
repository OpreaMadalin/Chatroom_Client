package com.company.service.authService;

import com.company.exceptions.IncorrectCredentialsException;
import com.company.service.clientInteraction.ClientRegisterLoginImpl;
import com.company.util.getHost.GetHost;
import com.company.util.getHost.GetHostImpl;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

public class AuthServiceImpl implements AuthService {

    public static String token;
    GetHost host = new GetHostImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void register() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Username --");
        jsonObject.put("username", scanner.nextLine());
        System.out.println("-- Insert Password --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/register"))
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();

            System.out.println("Register Successfully!");
            String userId = httpResponse.getBody().getObject().getString("registeredID");
            System.out.println("User ID: " + userId);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void login() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Username --");
        jsonObject.put("username", scanner.nextLine());
        System.out.println("-- Password --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/login"))
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();

            boolean tokenResult = httpResponse.isSuccess();
            try {
                if (!tokenResult) {
                    throw new IncorrectCredentialsException();
                } else {
                    token = String.valueOf(httpResponse.getBody().getObject().get("token"));
                    System.out.println("Login Successfully!");
                }
            } catch (Exception ex) {
                System.out.println("Incorrect Credentials!");
                ClientRegisterLoginImpl clientRegisterLogin = new ClientRegisterLoginImpl();
                clientRegisterLogin.initInteraction();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    public static String getToken() {
        return token;
    }
}
