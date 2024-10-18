package com.spring_start.spring_start.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {

    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    @Getter
    @Setter
    public static class Clouds {
        public int all;
    }

    @Getter
    @Setter
    public static class Coord {
        public int lon;
        public int lat;
    }

    @Getter
    @Setter
    public static class Main {
        public double temp;
        @JsonProperty("feels_like")
        public double feelslike;
        @JsonProperty("temp_min")
        public double tempmin;
        @JsonProperty("temp_max")
        public double tempmax;
        public int pressure;
        public int humidity;
        @JsonProperty("sea_level")
        public int seaLevel;
        @JsonProperty("grnd_level")
        public int grndLevel;
    }

    @Getter
    @Setter
    public static class Sys {
        public String country;
        public int sunrise;
        public int sunset;
    }

    @Getter
    @Setter
    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    @Getter
    @Setter
    public static class Wind {
        public double speed;
        public int deg;
        public double gust;
    }
}
