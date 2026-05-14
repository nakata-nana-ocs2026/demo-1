package com.example.demo.model;

public class Player {
    public String id;
    public int hp = 100;
    public String name;

    public Player(String id) {
        this.id = id;
    }

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }
}