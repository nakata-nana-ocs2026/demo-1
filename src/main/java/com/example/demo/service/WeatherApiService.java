package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WeatherApiService {

    private final Random random = new Random();

    public Question getRandomQuestion() {

        List<Question> questions = loadQuestions();

        return questions.get(
                random.nextInt(questions.size())
        );
    }

    private List<Question> loadQuestions() {

        List<Question> questions = new ArrayList<>();

        try {
            URL url = new URL(
                    "https://www.data.jma.go.jp/stats/data/mdrr/tem_rct/alltable/mxtemsadext00_rct.csv"
            );

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            url.openStream(),
                            StandardCharsets.UTF_8
                    )
            );

            String line;
            int count = 0;

            while ((line = br.readLine()) != null && count < 20) {

                if (count == 0) {
                    count++;
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 5) continue;

                double temp = parseDouble(data[4]);

                int month = randomMonth();

                String region = randomRegion();

                String type = classifyWeather(month);

                questions.add(
                        new Question(
                                region,
                                month,
                                type,
                                temp,
                                randomRainfall(month),
                                randomSunshine(month),
                                generateHint(type)
                        )
                );

                count++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 20.0;
        }
    }

    private int randomMonth() {
        int[] months = {3, 4, 9, 10};
        return months[random.nextInt(months.length)];
    }

    private String randomRegion() {
        String[] regions = {
                "北海道", "東北", "関東",
                "中部", "関西", "中国",
                "四国", "九州"
        };
        return regions[random.nextInt(regions.length)];
    }

    private String classifyWeather(int month) {
        if (month == 3) return "雪解け型";
        if (month == 4) return "春型";
        if (month == 9) return "台風型";
        return "秋雨型";
    }

    private double randomRainfall(int month) {
        if (month == 9) return 200 + random.nextInt(100);
        if (month == 10) return 150 + random.nextInt(80);
        return 80 + random.nextInt(60);
    }

    private double randomSunshine(int month) {
        return 120 + random.nextInt(80);
    }

    private String generateHint(String type) {
        return switch (type) {
            case "雪解け型" -> "雪解け開始";
            case "春型" -> "花冷えあり";
            case "台風型" -> "台風接近";
            default -> "秋雨前線";
        };
    }
}