package com.company.service.authService;


import com.company.service.exception.IncorrectCredentialsException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

import java.util.Scanner;


public class AuthServiceImpl implements AuthService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void register() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Username --");
        jsonObject.put("username", scanner.nextLine());
        System.out.println("-- Insert Password --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse httpResponse = Unirest.post("http://localhost:8080/register")
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();
            System.out.println("Register Successfully!");
            System.out.println(httpResponse.getBody());
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
            HttpResponse httpResponse = Unirest.post("http://localhost:8080/login")
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();
            // String token = String.valueOf(httpResponse.getBody());
            boolean tokenResult = httpResponse.isSuccess();
            try {
                if (!tokenResult) {
                    throw new IncorrectCredentialsException();
                } else {
                    System.out.println("Login Successfully!");
                }
            } catch (Exception ex) {
                System.out.println("Incorrect Credentials!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
