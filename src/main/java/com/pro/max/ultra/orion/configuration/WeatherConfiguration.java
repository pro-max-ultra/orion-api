package com.pro.max.ultra.orion.configuration;

import com.pro.max.ultra.orion.function.weather.GetWeatherFunction;
import com.pro.max.ultra.orion.property.WeatherProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;


@Configuration
public class WeatherConfiguration {

    @Bean
    @Description("Get the current weather conditions for the given city.")
    public Function<GetWeatherFunction.Request,GetWeatherFunction.Response> getWeatherFunction(WeatherProperties weatherProperties) {
        return new GetWeatherFunction(weatherProperties);
    }
}