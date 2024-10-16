package com.pro.max.ultra.orion.function.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.function.Function;

@Slf4j
public class CreateTaskFunction implements Function<CreateTaskFunction.Request, CreateTaskFunction.Response> {

    private final WebClient webClient;

    public CreateTaskFunction(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Response apply(Request request) {
        log.info("Create Task Request: {}", request);

        Response response = webClient
                .post()
                .uri("/api/tasks")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        log.info("Task Service Response: {}", response);
        return response;
    }

    public record Request(String title, String description, String location, boolean completed, HashMap<String, String> metadata) {}

    public record Response(Long id, String title, String description, String location, boolean completed, Object metadata) {}
}