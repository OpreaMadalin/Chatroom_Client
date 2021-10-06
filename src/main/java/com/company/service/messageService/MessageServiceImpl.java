package com.company.service.messageService;

import com.company.service.authService.TokenService;
import com.company.util.getHost.GetHost;
import com.company.util.getHost.GetHostImpl;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MessageServiceImpl implements MessageService {

    private BufferedReader reader;
    GetHost host = new GetHostImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addChatroomMessage() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Message --");
        jsonObject.put("message", scanner.nextLine());
        System.out.println("-- Insert Chatroom Password Or Leave it Blank If Doesn't Have One --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/addChatroomMessage"))
                    .header("Authorization", TokenService.getToken())
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();
            boolean status = httpResponse.isSuccess();
            if (status) {
                System.out.println("Send!");
            } else {
                System.out.println("Message Not Send!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showChatroomMessages() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Chatroom Password Or Leave it Blank If Doesn't Have One --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/getChatroomMessages"))
                    .header("Authorization", TokenService.getToken())
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();

            boolean status = httpResponse.isSuccess();

            if (status) {
                JSONObject msg = httpResponse.getBody().getObject();
                JSONArray arr = msg.getJSONArray("chatroomMessages");
                for (int i = 0; i < arr.length(); i++) {
                    String timestamp = arr.getJSONObject(i).getString("timestamp");
                    String username = arr.getJSONObject(i).getString("username");
                    String message = arr.getJSONObject(i).getString("message");
                    System.out.println("Date: " + timestamp + " Sender: " + username + " Message: " + message);
                }
            } else {
                System.out.println("Unsuccessful Request!");
            }


        } catch (
                UnirestException e) {
            e.printStackTrace();
        }
    }

}
