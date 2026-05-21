package com.example.demo.model;

public class WeatherData {

    private double temperature;
    private double rainfall;
    private double sunshine;

    private String hint1;
    private String hint2;

    public WeatherData(
            double temperature,
            double rainfall,
            double sunshine,
            String hint1,
            String hint2
    ) {
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.sunshine = sunshine;
        this.hint1 = hint1;
        this.hint2 = hint2;
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