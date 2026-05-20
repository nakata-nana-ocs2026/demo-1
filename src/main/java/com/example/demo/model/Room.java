package com.example.demo.model;

import java.util.List;

public class Room {

    private String roomId;
    private String roomName;

    private List<Team> teams = new java.util.ArrayList<>();

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

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}