package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final WeatherApiService weatherApiService;

    private Answer correctAnswer;

    public GameService(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public WeatherData startGame() {

        Question question = weatherApiService.getRandomQuestion();

        correctAnswer = new Answer();

        correctAnswer.setRegion(question.getRegion());
        correctAnswer.setMonth(question.getMonth());
        correctAnswer.setWeatherType(question.getWeatherType());

        return new WeatherData(
                question.getTemperature(),
                question.getRainfall(),
                question.getSunshine(),
                question.getHint()
        );
    }

    public JudgeResult checkAnswer(Answer userAnswer) {

        int hit = 0;

        if (userAnswer.getRegion()
                .equals(correctAnswer.getRegion())) {
            hit++;
        }

        if (userAnswer.getMonth()
                == correctAnswer.getMonth()) {
            hit++;
        }

        if (userAnswer.getWeatherType()
                .equals(correctAnswer.getWeatherType())) {
            hit++;
        }

        return new JudgeResult(hit, hit == 3);
    }
}