package com.pro.max.ultra.orion.function.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class GetAllTasksFunction implements Function<Void, List<GetAllTasksFunction.Response>> {

    private final WebClient webClient;

    public GetAllTasksFunction(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Response> apply(Void unused) {
        log.info("Get All Tasks Request");

        List<Response> response = webClient.get()
                .uri("/api/tasks")
                .retrieve()
                .bodyToFlux(Response.class)
                .collectList()
                .block();

        log.info("Task Service Response: {}", response);
        return response;
    }

    public record Response(Long id, String title, String description, String location, boolean completed, Object metadata) {}
}