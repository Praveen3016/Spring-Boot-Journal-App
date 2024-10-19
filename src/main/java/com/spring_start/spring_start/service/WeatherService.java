package com.spring_start.spring_start.service;

import com.spring_start.spring_start.api.response.WeatherResponse;
import com.spring_start.spring_start.cache.AppCache;
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

//    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private AppCache appCache ;

    public WeatherResponse getWeatherData(String city){
        String finalapi = appCache.APP_CACHE.get("weather_api").replace("<city>" , city).replace("<api_key>" , apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi , HttpMethod.GET , null , WeatherResponse.class);
       WeatherResponse body = response.getBody();
       return  body ;
    }
}
