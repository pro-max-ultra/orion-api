package com.pro.max.ultra.orion.configuration;

import com.pro.max.ultra.orion.function.task.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Function;

@Configuration
public class TaskConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("http://localhost:8080").build();
    }
    @Bean
    @Description("Get all tasks for the user.")
    public Function<Void, List<GetAllTasksFunction.Response>>  getAllTasksFunction(WebClient taskWebClient) {
        return new GetAllTasksFunction(taskWebClient);
    }
    @Bean
    @Description("Get a task for the user.")
    public Function<GetTaskFunction.Request, GetTaskFunction.Response> getTaskFunction(WebClient taskWebClient) {
        return new GetTaskFunction(taskWebClient);
    }

    @Bean
    @Description("Create a new task for the user.")
    public Function<CreateTaskFunction.Request, CreateTaskFunction.Response> createTaskFunction(WebClient taskWebClient) {
        return new CreateTaskFunction(taskWebClient);
    }


    @Bean
    @Description("Update a task for the user.")
    public Function<UpdateTaskFunction.Request,UpdateTaskFunction.Response>  updateTaskFunction(WebClient taskWebClient) {
        return new UpdateTaskFunction(taskWebClient);
    }

    @Bean
    @Description("Delete a task for the user.")
    public Function<DeleteTaskFunction.Request,Void>  deleteTaskFunction(WebClient taskWebClient) {
        return new DeleteTaskFunction(taskWebClient);
    }
}