package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamId;
    private String teamName;

    private List<Player> players = new ArrayList<>();

    public Team(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public String getteamId() {
        return teamId;
    }

    // public String getTeamId() {
    //     return teamId;
    // }

    // public void setTeamId(String teamId) {
    //     this.teamId = teamId;
    // }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }
}