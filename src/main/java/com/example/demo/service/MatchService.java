package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final WeatherApiService apiService;

    private Answer correctAnswer;

    public MatchService(
            WeatherApiService apiService
    ) {
        this.apiService = apiService;
    }

    // =====================================================
    // ゲーム開始
    // =====================================================

    public WeatherData startGame() {

        // ランダム問題取得
        Question q =
                apiService.getRandomQuestion();

        // 正解保存
        correctAnswer = new Answer();

        correctAnswer.setRegion(
                q.getRegion()
        );

        correctAnswer.setMonth(
                q.getMonth()
        );

        correctAnswer.setWeatherType(
                q.getWeatherType()
        );

        // 画面表示データ返却
        return new WeatherData(
                q.getTemperature(),
                q.getRainfall(),
                q.getSunshine(),
                q.getHint1(),
                q.getHint2()
        );
    }

    // =====================================================
    // 回答判定
    // =====================================================

    public JudgeResult checkAnswer(
            Answer userAnswer
    ) {

        int hit = 0;

        // 地方判定
        if (
                userAnswer.getRegion()
                        .equals(correctAnswer.getRegion())
        ) {
            hit++;
        }

        // 月判定
        if (
                userAnswer.getMonth()
                        == correctAnswer.getMonth()
        ) {
            hit++;
        }

        // 天気タイプ判定
        if (
                userAnswer.getWeatherType()
                        .equals(correctAnswer.getWeatherType())
        ) {
            hit++;
        }

        // 3Hitならクリア
        return new JudgeResult(
                hit,
                hit == 3
        );
    }
}