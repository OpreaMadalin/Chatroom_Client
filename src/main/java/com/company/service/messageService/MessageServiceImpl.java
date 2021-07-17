package com.company.service.messageService;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

import static com.company.util.Constants.TOKEN;

public class MessageServiceImpl implements MessageService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addMessage() {

    }

    @Override
    public void showAllMessagesFromChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/getChatroomMessages")
                    .header("Authorization", TOKEN)
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();

            JSONObject msg = httpResponse.getBody().getObject();

            JSONArray arr = msg.getJSONArray("chatroomMessages");

            for (int i = 0; i < arr.length(); i++) {
                String timestamp = arr.getJSONObject(i).getString("timestamp");
                String username = arr.getJSONObject(i).getString("username");
                String message = arr.getJSONObject(i).getString("message");

                System.out.println("Date: " + timestamp + " Sender: " + username + " Message: " + message);
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
