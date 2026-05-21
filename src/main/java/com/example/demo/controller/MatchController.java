package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService service;

    public MatchController(
            MatchService service
    ) {
        this.service = service;
    }

    @GetMapping("/start")
    public WeatherData startGame() {
        return service.startGame();
    }

    @PostMapping("/check")
    public JudgeResult checkAnswer(
            @RequestBody Answer answer
    ) {
        return service.checkAnswer(answer);
    }
}