package dev.com.mcp.application.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.com.mcp.application.models.ChatCompletionChoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatCompletionsResponse {

    private List<ChatCompletionChoice> choices;
}
