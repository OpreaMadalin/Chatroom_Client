package com.company.service.chatroomService;

import com.company.service.authService.TokenService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;


public class ChatroomServiceImpl implements ChatroomService {

    private final Scanner scanner = new Scanner(System.in);


    @Override
    public void addChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Password For Chatroom --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/addChatroom")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean addCht = httpResponse.isSuccess();
            if (addCht) {
                System.out.println("Chatroom Successfully Added!");
            } else {
                System.out.println("Chatroom Not Added!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getChatrooms() {

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.get("http://localhost:8080/getChatrooms")
                    .header("Authorization", TokenService.getToken())
                    .asJson();

            boolean getCht = httpResponse.isSuccess();
            if (getCht) {
                JSONObject cht = httpResponse.getBody().getObject();
                JSONArray arr = cht.getJSONArray("chatrooms");

                for (int i = 0; i < arr.length(); i++) {
                    String chatroom = arr.getString(i);
                    System.out.println("Chatroom Name: " + chatroom);
                }
            } else {
                System.out.println("Unsuccessful Request!");
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
        System.out.println("-- Insert Chatroom Password --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.delete("http://localhost:8080/deleteChatroom")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean deleteCht = httpResponse.isSuccess();
            if (deleteCht) {
                System.out.println("Chatroom Successfully Deleted!");
            } else {
                System.out.println("Chatroom Not Deleted!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateChatroomName() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert New Chatroom Name --");
        jsonObject.put("newChatroomName", scanner.nextLine());
        System.out.println("-- Insert Chatroom Password --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/updateChatroomName")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean updateCht = httpResponse.isSuccess();
            if (updateCht) {
                System.out.println("Chatroom Successfully Renamed!");
            } else {
                System.out.println("Chatroom Not Renamed!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateChatroomPassword() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Old Password --");
        jsonObject.put("password", scanner.nextLine());
        System.out.println("-- Insert New Password --");
        jsonObject.put("newPassword", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/updateChatroomPassword")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean updateChtName = httpResponse.isSuccess();
            if (updateChtName) {
                System.out.println("Chatroom Password Successfully Changed!");
            } else {
                System.out.println("Chatroom Password Not Changed!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAdmin() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Admin Name --");
        jsonObject.put("adminName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/addAdmin")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean addAdmin = httpResponse.isSuccess();
            if (addAdmin) {
                System.out.println("Successfully Added!");
            } else {
                System.out.println("Not Added!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBannedUser() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert A User To Be Banned --");
        jsonObject.put("bannedUser", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post("http://localhost:8080/banUser")
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean addAdmin = httpResponse.isSuccess();
            if (addAdmin) {
                System.out.println("Successfully Added!");
            } else {
                System.out.println("Not Added!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}
