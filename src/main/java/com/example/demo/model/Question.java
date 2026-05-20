package com.example.demo.model;

public class Question {

    private String region;
    private int month;
    private String weatherType;

    private double temperature;
    private double rainfall;
    private double sunshine;

    private String hint1;
    private String hint2;

    public Question(
            String region,
            int month,
            String weatherType,
            double temperature,
            double rainfall,
            double sunshine,
            String hint1,
            String hint2
    ) {
        this.region = region;
        this.month = month;
        this.weatherType = weatherType;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.sunshine = sunshine;
        this.hint1 = hint1;
        this.hint2 = hint2;
    }

    public String getRegion() {
        return region;
    }

    public int getMonth() {
        return month;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getRainfall() {
        return rainfall;
    }

    public double getSunshine() {
        return sunshine;
    }

    public String getHint1() {
        return hint1;
    }

    public String getHint2() {
        return hint2;
    }
}