package com.pro.max.ultra.orion.function.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Function;

@Slf4j
public class DeleteTaskFunction implements Function<DeleteTaskFunction.Request, Void> {

    private final WebClient webClient;

    public DeleteTaskFunction(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Void apply(Request request) {
        log.info("Delete Task Request: {}", request);

        webClient.delete()
                .uri("/api/tasks/{id}", request.id())
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        log.info("Task Deleted Successfully");
        return null;
    }

    public record Request(Long id) {}
}