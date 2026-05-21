package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final WeatherApiService weatherApiService;

    private Answer correctAnswer;

    public GameService(
            WeatherApiService weatherApiService
    ) {
        this.weatherApiService =
                weatherApiService;
    }

    // =====================================================
    // ゲーム開始
    // =====================================================

    public WeatherData startGame() {

        Question question =
                weatherApiService.getRandomQuestion();

        // 正解保存
        correctAnswer = new Answer();

        correctAnswer.setRegion(
                question.getRegion()
        );

        correctAnswer.setMonth(
                question.getMonth()
        );

        correctAnswer.setWeatherType(
                question.getWeatherType()
        );

        // 画面表示用データ
        return new WeatherData(

                question.getTemperature(),

                question.getRainfall(),

                question.getSunshine(),

                question.getHint1(),

                question.getHint2()
        );
    }

    // =====================================================
    // 回答判定
    // =====================================================

    public JudgeResult checkAnswer(
            Answer userAnswer
    ) {

        int hit = 0;

        // 地方
        if (
                userAnswer.getRegion()
                        .equals(correctAnswer.getRegion())
        ) {
            hit++;
        }

        // 月
        if (
                userAnswer.getMonth()
                        == correctAnswer.getMonth()
        ) {
            hit++;
        }

        // 天気タイプ
        if (
                userAnswer.getWeatherType()
                        .equals(correctAnswer.getWeatherType())
        ) {
            hit++;
        }

        // 3Hitでクリア
        return new JudgeResult(
                hit,
                hit == 3
        );
    }
}