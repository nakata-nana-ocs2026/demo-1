package com.example.demo.chat;

import org.springframework.web.bind.annotation.*;

import com.example.demo.chat.message.ChatMessage;

import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class ChatController {

    private final List<ChatMessage> messages = new ArrayList<>();

    @PostMapping
    public void sendMessage(@RequestBody String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);
            messages.add(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<ChatMessage> getMessages() {
        return messages;
    }
}