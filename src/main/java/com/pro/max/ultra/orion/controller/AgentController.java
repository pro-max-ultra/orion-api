package com.pro.max.ultra.orion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
class AgentController {
	private final ChatClient chatClient;

	AgentController(ChatClient.Builder builder) {
		log.debug("Creating TaskAgentController with builder: " + builder);
		this.chatClient =  builder
				.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.defaultSystem("You are a helpful AI Assistant helping a user manager their task list.  If the user asks for their task, don't need to confirm, just do it.")
                .defaultFunctions("getWeatherFunction",
                        "getAllTasksFunction",
                        "getTaskFunction",
                        "createTaskFunction",
                        "updateTaskFunction",
                        "deleteTaskFunction")
				.build();
	}

	@GetMapping("/ai")
	String completion(@RequestParam(value = "message") String message) {
		log.debug("Here is the message: " + message);
		return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
	}

	// http --stream :8080/stream
	@GetMapping("/stream")
	public Flux<String> stream(@RequestParam(
			value = "message",
			defaultValue = "I'm visiting San Francisco next month, what are 10 places I must visit?") String message) {
		return chatClient
				.prompt()
				.user(message)
				.stream()
				.content();
	}
}
