package com.pro.max.ultra.orion.function.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Function;

@Slf4j
public class GetTaskFunction implements Function<GetTaskFunction.Request, GetTaskFunction.Response> {

    private final WebClient webClient;

    public GetTaskFunction(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Response apply(Request request) {
        log.info("Get Task Request: {}", request);

        Response response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/tasks/{id}")
                        .build(request.taskId()))
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        log.info("Task Service Response: {}", response);
        return response;
    }

    public record Request(Long taskId) {}

    public record Response(Long id, String title, String description, String location, boolean completed, Object metadata) {}
}