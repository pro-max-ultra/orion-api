package com.pro.max.ultra.orion.function.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Function;

@Slf4j
public class UpdateTaskFunction implements Function<UpdateTaskFunction.Request, UpdateTaskFunction.Response> {

    private final WebClient webClient;

    public UpdateTaskFunction(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Response apply(Request request) {
        log.info("Update Task Request: {}", request);

        Response response = webClient.put()
                .uri("/api/tasks/{id}", request.id())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        log.info("Task Service Response: {}", response);
        return response;
    }

    public record Request(Long id, String title, String description, String location, boolean completed, Object metadata) {}

    public record Response(Long id, String title, String description, String location, boolean completed, Object metadata) {}
}