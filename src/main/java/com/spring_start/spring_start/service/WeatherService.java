package com.spring_start.spring_start.service;

import com.spring_start.spring_start.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private  String apiKey ;

    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";

    @Autowired
    private RestTemplate restTemplate ;

    public WeatherResponse getWeatherData(String city){
        String finalapi = API.replace("CITY" , city).replace("API_KEY" , apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi , HttpMethod.GET , null , WeatherResponse.class);
       WeatherResponse body = response.getBody();
       return  body ;
    }
}
