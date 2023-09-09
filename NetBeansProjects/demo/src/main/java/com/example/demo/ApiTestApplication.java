package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTestApplication.class, args);
        WeatherTest();
        System.exit(0);
    }

    public static void WeatherTest() {
        try {
            String url = "https://goweather.herokuapp.com/weather/London";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonTemp = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonTemp);

            // Send get request for wind speed and temperature (in celsius)
            String windSpeed = root.findValue("wind").asText();
            String cityTemp = root.findValue("temperature").asText();
            String description = root.findValue("description").asText();

            System.out.println("Wind speed in London: " + windSpeed
                    + "\nTemperature in London: " + cityTemp
                    + "\nWeather Description: " + description);
        } catch (JsonProcessingException ex) {
            System.out.println("Error");
        }
    }
}
