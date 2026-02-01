package dev.com.mcp.application.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "mcp.orchestrator")
public class OrchestratorProperties {

    @NotNull
    @Min(1)
    private Integer maxAttempts;
}
