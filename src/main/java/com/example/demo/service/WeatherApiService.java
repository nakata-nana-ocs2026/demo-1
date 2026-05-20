package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class WeatherApiService {

private final RestTemplate restTemplate =
        new RestTemplate();

private final Random random =
        new Random();

// 地方コード
private final Map<String, String> regionCodes =
        Map.of(
                "北海道", "016000",
                "東北", "040000",
                "関東", "130000",
                "中部", "230000",
                "関西", "270000",
                "中国", "340000",
                "四国", "380000",
                "九州", "400000"
        );

// 地方一覧
private final List<String> regions =
        List.of(
                "北海道",
                "東北",
                "関東",
                "中部",
                "関西",
                "中国",
                "四国",
                "九州"
        );

public Question getRandomQuestion() {

// -------------------------
// 地方ランダム
// -------------------------
String region =
        regions.get(
                random.nextInt(regions.size())
        );

// -------------------------
// 月ランダム
// -------------------------
int[] months = {3, 4, 9, 10};

int month =
        months[random.nextInt(months.length)];

// -------------------------
// 気象庁API接続
// -------------------------
String code =
        regionCodes.get(region);

String url =
        "https://www.jma.go.jp/bosai/forecast/data/forecast/"
                + code
                + ".json";

// 接続確認用
Object[] response =
        restTemplate.getForObject(
                url,
                Object[].class
        );

// -------------------------
// ゲーム用データ生成
// -------------------------
double temperature =
        generateTemperature(region, month);

double rainfall =
        generateRainfall(month);

double sunshine =
        generateSunshine(month);

// -------------------------
// 天気タイプ判定
// -------------------------
String weatherType =
        judgeWeatherType(
                temperature,
                rainfall,
                sunshine
        );

// -------------------------
// ヒント生成
// -------------------------
String hint1 =
        createRegionHint(region);

String hint2 =
        createWeatherHint(weatherType);

// -------------------------
// Question生成
// -------------------------
return new Question(
        region,
        month,
        weatherType,
        temperature,
        rainfall,
        sunshine,
        hint1,
        hint2
);
}

// =====================================================
// 気温生成
// =====================================================

private double generateTemperature(
        String region,
        int month
) {

if (month == 3) {

        if (region.equals("北海道")) {
        return randomRange(-2, 5);
        }

        return randomRange(4, 12);
}

if (month == 4) {
        return randomRange(10, 18);
}

if (month == 9) {
        return randomRange(24, 30);
}

return randomRange(15, 23);
}

// =====================================================
// 降水量生成
// =====================================================

private double generateRainfall(int month) {

if (month == 9) {
        return randomRange(220, 350);
}

if (month == 10) {
        return randomRange(120, 220);
}

return randomRange(70, 140);
}

// =====================================================
// 日照時間生成
// =====================================================

private double generateSunshine(int month) {

if (month == 9) {
        return randomRange(120, 170);
}

return randomRange(160, 230);
}

// =====================================================
// 天気タイプ判定
// =====================================================

private String judgeWeatherType(
        double temperature,
        double rainfall,
        double sunshine
) {

// 寒冷型
if (temperature <= 8) {
return "寒冷型";
}

// 豪雨型
if (rainfall >= 220) {
return "豪雨型";
}

// 快晴型
if (
sunshine >= 180
&& rainfall <= 120
) {
return "快晴型";
}

// ぐずつき型
return "ぐずつき型";
}

// =====================================================
// 地方ヒント
// =====================================================

private String createRegionHint(
        String region
) {

switch (region) {

        case "北海道":
        return "雪が残る地域";

        case "東北":
        return "桜前線が遅め";

        case "関東":
        return "都市部は暑くなりやすい";

        case "中部":
        return "山沿いで寒暖差";

        case "関西":
        return "蒸し暑さが特徴";

        case "中国":
        return "日本海側は雨が多い";

        case "四国":
        return "晴天率が高い";

        case "九州":
        return "台風の影響を受けやすい";

        default:
        return "季節の変わり目";
}
}

// =====================================================
// 天気タイプヒント
// =====================================================

private String createWeatherHint(
        String weatherType
) {

switch (weatherType) {

        case "雪解け型":
        return "気温がかなり低い";

        case "春型":
        return "日照時間が長い";

        case "台風型":
        return "降水量が非常に多い";

        case "秋雨型":
        return "雨の日が続きやすい";

        default:
        return "天候が不安定";
}
}

// =====================================================
// ランダム範囲生成
// =====================================================

private double randomRange(
        double min,
        double max
) {

return Math.round(
        (
                min
                        + random.nextDouble()
                        * (max - min)
        ) * 10
) / 10.0;
}
}