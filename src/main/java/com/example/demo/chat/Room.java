package com.example.demo.chat;

public class Room {

    private Long id;

    private String name;

    // 追加
    private Long playerId;

    public Room() {
    }

    public Room(Long id, String name, Long playerId) {
        this.id = id;
        this.name = name;
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}