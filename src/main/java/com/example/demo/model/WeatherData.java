package com.example.demo.model;

public class WeatherData {

    private double temperature;
    private double rainfall;
    private double sunshine;
    private String hint;

    public WeatherData(
            double temperature,
            double rainfall,
            double sunshine,
            String hint
    ) {
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.sunshine = sunshine;
        this.hint = hint;
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

    public String getHint() {
        return hint;
    }
}