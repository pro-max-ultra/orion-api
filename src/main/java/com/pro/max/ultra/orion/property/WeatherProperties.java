package com.pro.max.ultra.orion.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather")
@Data
public class WeatherProperties {
    private String apiUrl;
    private String apiKey;
}