package com.company.service.messageService;

import com.company.service.authService.TokenService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;


public class MessageServiceImpl implements MessageService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addChatroomMessage() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Message --");
        jsonObject.put("messages", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/addChatroomMessage")
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
    public void showAllMessagesFromChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/getChatroomMessages")
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


        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
