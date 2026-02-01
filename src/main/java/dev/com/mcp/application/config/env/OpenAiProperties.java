package dev.com.mcp.application.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "openai")
public class OpenAiProperties {

    @NotBlank
    private String apiKey;

    @NotBlank
    private String model;

    @NotBlank
    private String toolChoice;
}
