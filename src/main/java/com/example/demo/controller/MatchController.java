package com.example.demo.controller;

import com.example.demo.model.Answer;
import com.example.demo.model.JudgeResult;
import com.example.demo.model.WeatherData;
import com.example.demo.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService service;

    public MatchController(MatchService service) {
        this.service = service;
    }

    // ゲーム開始
    @GetMapping("/start")
    public WeatherData startGame() {
        return service.startGame();
    }

    // 回答判定
    @PostMapping("/check")
    public JudgeResult checkAnswer(
            @RequestBody Answer answer
    ) {
        return service.checkAnswer(answer);
    }
}