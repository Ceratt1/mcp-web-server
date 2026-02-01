package dev.com.mcp.application.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatCompletionMessage {

    private String role;
    private Object content;

    @JsonProperty("tool_calls")
    private List<ToolCall> toolCalls;
}
