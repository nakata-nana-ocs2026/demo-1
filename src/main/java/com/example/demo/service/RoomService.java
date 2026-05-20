package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Player;
import com.example.demo.model.Room;
import com.example.demo.model.Team;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {

    private final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public Room createRoom(String roomId, String roomName) {
        Room room = new Room(roomId, roomName);
        rooms.put(roomId, room);
        return room;
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public Room joinTeam(
        String roomName,
        String teamName,
        String username) {

    String roomId = UUID.randomUUID().toString();
    String teamId = UUID.randomUUID().toString();
    String playerId = UUID.randomUUID().toString();

    Room room = new Room(roomId, roomName);

    Team team = new Team(teamId, teamName);

    Player player = new Player(playerId, username);

    team.addPlayer(player);

    room.addTeam(team);

    return room;
}

    public Collection<Room> getRooms() {
        return rooms.values();
    }
}