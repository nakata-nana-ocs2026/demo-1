package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WeatherApiService {

    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    public WeatherApiService() {

        // 春（3月）
        questions.add(new Question(
                "北海道", 3, "雪解け型",
                2.8, 78.4, 152.0,
                "雪解け開始"
        ));

        questions.add(new Question(
                "東北", 3, "雪解け型",
                5.2, 85.1, 165.0,
                "寒暖差あり"
        ));

        // 春（4月）
        questions.add(new Question(
                "関東", 4, "春型",
                14.6, 124.3, 188.0,
                "花冷えあり"
        ));

        questions.add(new Question(
                "中部", 4, "春型",
                13.2, 118.7, 182.0,
                "春一番"
        ));

        questions.add(new Question(
                "関西", 4, "春型",
                15.8, 110.4, 190.0,
                "桜満開"
        ));

        // 秋（9月）
        questions.add(new Question(
                "関東", 9, "台風型",
                26.1, 210.4, 142.0,
                "台風接近"
        ));

        questions.add(new Question(
                "関西", 9, "台風型",
                27.0, 228.2, 135.0,
                "強風あり"
        ));

        questions.add(new Question(
                "九州", 9, "台風型",
                27.8, 250.5, 128.0,
                "大雨注意"
        ));

        // 秋（10月）
        questions.add(new Question(
                "中部", 10, "秋雨型",
                18.9, 172.3, 166.0,
                "秋雨前線"
        ));

        questions.add(new Question(
                "関西", 10, "秋雨型",
                20.4, 160.2, 170.0,
                "朝晩冷える"
        ));

        questions.add(new Question(
                "九州", 10, "秋雨型",
                21.3, 175.6, 168.0,
                "秋晴れ少なめ"
        ));
    }

    public Question getRandomQuestion() {
        return questions.get(random.nextInt(questions.size()));
    }
}