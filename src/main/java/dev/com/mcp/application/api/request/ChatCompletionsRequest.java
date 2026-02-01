package dev.com.mcp.application.api.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionsRequest {

    @NotBlank
    private String model;

    @NotNull
    private List<Map<String, Object>> messages;

    @NotNull
    private List<Map<String, Object>> tools;

    @NotBlank
    @JsonProperty("tool_choice")
    private String toolChoice;
}
