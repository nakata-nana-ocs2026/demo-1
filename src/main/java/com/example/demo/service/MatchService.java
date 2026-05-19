package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final WeatherApiService apiService;

    private Answer correctAnswer;

    public MatchService(WeatherApiService apiService) {
        this.apiService = apiService;
    }

    public WeatherData startGame() {

        Question q = apiService.getRandomQuestion();

        correctAnswer = new Answer();
        correctAnswer.setRegion(q.getRegion());
        correctAnswer.setMonth(q.getMonth());
        correctAnswer.setWeatherType(q.getWeatherType());

        return new WeatherData(
                q.getTemperature(),
                q.getRainfall(),
                q.getSunshine(),
                q.getHint()
        );
    }

    public JudgeResult checkAnswer(Answer userAnswer) {

        int hit = 0;

        if (userAnswer.getRegion().equals(correctAnswer.getRegion())) {
            hit++;
        }

        if (userAnswer.getMonth() == correctAnswer.getMonth()) {
            hit++;
        }

        if (userAnswer.getWeatherType().equals(correctAnswer.getWeatherType())) {
            hit++;
        }

        boolean clear = hit == 3;

        return new JudgeResult(hit, clear);
    }
}