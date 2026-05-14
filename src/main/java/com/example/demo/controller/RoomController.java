package com.example.demo.controller;

import com.example.demo.dto.JoinRequest;
import com.example.demo.model.Player;
import com.example.demo.model.Room;
import com.example.demo.model.Team;
import com.example.demo.service.RoomService;

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
    public Room joinRoom(JoinRequest request) {
        System.out.println("参加リクエスト: " + request.getUsername());
         return roomService.joinTeam(
                request.getRoomName(),
                request.getTeamName(),
                request.getUsername()
          );

        // return roomService.getRoom(request.getRoomId());
    }
}