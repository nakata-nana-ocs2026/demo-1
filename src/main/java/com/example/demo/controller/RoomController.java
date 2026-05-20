package com.example.demo.controller;

import com.example.demo.dto.JoinRequest;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;

import tools.jackson.databind.ObjectMapper;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @MessageMapping("/join")
    @SendTo("/topic/rooms")
    public Room joinRoom(String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println("受信したJSON: " + jsonString);
            JoinRequest request = objectMapper.readValue(jsonString, JoinRequest.class);

            System.out.println("参加リクエスト: " + request.getPlayerName());

            return roomService.joinTeam(
                request.getRoomName(),
                request.getTeamName(),
                request.getPlayerName()
            );
        } catch (Exception e) {

            System.out.println("Error occurred while processing join request:");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @MessageExceptionHandler
    public void handleException(Throwable e) {
        System.out.println("Error handling WebSocket message: " + e.getMessage());
    }
}