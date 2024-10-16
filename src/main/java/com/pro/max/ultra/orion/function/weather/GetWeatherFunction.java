package com.pro.max.ultra.orion.function.weather;

import com.pro.max.ultra.orion.property.WeatherProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/*
   Weather API
   https://www.weatherapi.com/api-explorer.aspx
 */
public class GetWeatherFunction implements Function<GetWeatherFunction.Request, GetWeatherFunction.Response> {

    private static final Logger log = LoggerFactory.getLogger(GetWeatherFunction.class);
    private final RestClient restClient;
    private final WeatherProperties weatherProps;

    public GetWeatherFunction(WeatherProperties props) {
        this.weatherProps = props;
        log.debug("Weather API URL: {}", weatherProps.getApiUrl());
        log.debug("Weather API Key: {}", weatherProps.getApiKey());
        this.restClient = RestClient.create(weatherProps.getApiUrl());
    }

    @Override
    public Response apply(GetWeatherFunction.Request weatherRequest) {
        log.info("Weather Request: {}",weatherRequest);
        Response response = restClient.get()
                .uri("/current.json?key={key}&q={q}", weatherProps.getApiKey(), weatherRequest.city())
                .retrieve()
                .body(Response.class);
        log.info("Weather API Response: {}", response);
        return response;
    }

    // mapping the response of the Weather API to records. I only mapped the information I was interested in.
    public record Request(String city) {}
    public record Response(Location location,Current current) {}
    public record Location(String name, String region, String country, Long lat, Long lon){}
    public record Current(String temp_f, Condition condition, String wind_mph, String humidity) {}
    public record Condition(String text){}

}