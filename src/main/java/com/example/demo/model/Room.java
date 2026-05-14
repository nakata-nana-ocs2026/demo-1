package com.example.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private String roomId;
    private String roomName;

    private Map<String, List<String>> teams = new HashMap<>();

    public Room(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public Map<String, List<String>> getTeams() {
        return teams;
    }
}


// public class Room {

//     private String roomId;

//     private String roomName;

//     private Map<String, Team> teams = new HashMap<>();

//     public Room(String roomId, String roomName) {
//         this.roomId = roomId;
//         this.roomName = roomName;
//     }

//     public String getRoomId() {
//         return roomId;
//     }

//     public String getRoomName() {
//         return roomName;
//     }

//     public Map<String, Team> getTeams() {
//         return teams;
//     }
// }