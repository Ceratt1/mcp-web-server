package dev.com.mcp.application.api.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class McpResponse {

    @NotNull
    private String output;

    @NotNull
    private List<String> toolCalls;
}
