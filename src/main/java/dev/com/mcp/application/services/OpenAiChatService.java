package dev.com.mcp.application.services;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.com.mcp.application.api.request.ChatCompletionsRequest;
import dev.com.mcp.application.api.response.ChatCompletionsResponse;
import dev.com.mcp.application.config.env.OpenAiProperties;
import dev.com.mcp.application.exceptions.OpenAiException;

@Service
public class OpenAiChatService {

    private final RestClient restClient;
    private final OpenAiProperties properties;
    private final ObjectMapper objectMapper;

    public OpenAiChatService(
            ObjectMapper objectMapper,
            OpenAiProperties properties) {
        if (properties.getApiKey() == null || properties.getApiKey().isBlank()) {
            throw new IllegalStateException("OPENAI_API_KEY not set");
        }
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getApiKey())
                .build();
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    public ChatCompletionsResponse createChatCompletion(List<Map<String, Object>> messages,
            List<Map<String, Object>> tools) {
        ChatCompletionsRequest body = new ChatCompletionsRequest(
                properties.getModel(),
                messages,
                tools,
                properties.getToolChoice());

        String responseBody = restClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    String errorBody = response.getBody() == null
                            ? ""
                            : StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
                    String message = errorBody.isBlank() ? "Resposta vazia da OpenAI" : errorBody;
                    throw new OpenAiException(response.getStatusCode().value(), message);
                })
                .body(String.class);

        if (responseBody == null || responseBody.isBlank()) {
            throw new OpenAiException(200, "Resposta vazia da OpenAI");
        }
        try {
            return objectMapper.readValue(responseBody, ChatCompletionsResponse.class);
        } catch (Exception ex) {
            throw new OpenAiException(200, "Resposta invalida da OpenAI: " + ex.getMessage());
        }
    }

}
