package com.company.service.chatroomService;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

import static com.company.util.Constants.TOKEN;

public class ChatroomServiceImpl implements ChatroomService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void getChatrooms() {

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.get("http://localhost:8080/chatrooms")
                    .header("Authorization", TOKEN)
                    .asJson();

            JSONObject cht = httpResponse.getBody().getObject();
            JSONArray arr = cht.getJSONArray("chatrooms");

            for (int i = 0; i < arr.length(); i++) {
                String chatroom = arr.getString(i);
                System.out.println("Chatroom Name: " + chatroom);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/chatrooms")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TOKEN)
                    .body(jsonObject)
                    .asJson();
            boolean addCht = httpResponse.isSuccess();
            if (addCht) {
                System.out.println("Chatroom Successfully Added!");
            } else {
                System.out.println("Chatroom Not Added");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom --");
        jsonObject.put("chatroomName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.delete("http://localhost:8080/chatrooms")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TOKEN)
                    .body(jsonObject)
                    .asJson();
            boolean addCht = httpResponse.isSuccess();
            if (addCht) {
                System.out.println("Chatroom Successfully Deleted!");
            } else {
                System.out.println("Chatroom Not Deleted Or Not Exist!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateChatroom() {

    }
}
