package com.company.service.chatroomService;

import com.company.service.authService.TokenService;
import com.company.util.getHost.GetHost;
import com.company.util.getHost.GetHostImpl;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

public class ChatroomServiceImpl implements ChatroomService {

    GetHost host = new GetHostImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert Password For Chatroom? Press 1 for YES or 2 for NO --");

        String result = scanner.nextLine();
        if (result.equals(String.valueOf(1))) {
            System.out.println("-- Insert Password --");
            jsonObject.put("password", scanner.nextLine());
        } else if (result.equals(String.valueOf(2))) {
            jsonObject.put("password", "");
        }

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/addChatroom"))
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
            HttpResponse<JsonNode> httpResponse = Unirest.get(host.getHost().concat("/getChatrooms"))
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
        System.out.println("-- Insert Chatroom Password Or Leave it Blank If Doesn't Have One --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.delete(host.getHost().concat("/deleteChatroom"))
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
        System.out.println("-- Insert Chatroom Password Or Leave it Blank If Doesn't Have One --");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/updateChatroomName"))
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
        System.out.println("-- Insert Old Password Or Leave It Blank If Doesn't Have One --");
        jsonObject.put("password", scanner.nextLine());
        System.out.println("-- Insert New Password Or Leave It Blank For None --");
        jsonObject.put("newPassword", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/updateChatroomPassword"))
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
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/addAdmin"))
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
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/banUser"))
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
    public void listAdmins() {

        JSONObject jsonObject = new JSONObject();
        System.out.println("-- Insert Chatroom Name --");
        jsonObject.put("chatroomName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/getAdmins"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenService.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean getAdmins = httpResponse.isSuccess();

            if (getAdmins) {
                JSONObject chat = httpResponse.getBody().getObject();

                JSONArray admins = chat.getJSONArray("admins");

                for (int i = 0; i < admins.length(); i++) {
                    String string = admins.getString(i);
                    System.out.println("ADMIN: " + string);
                }
            } else {
                System.out.println("You are not admin at this chatroom!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}
