package dev.com.mcp.application.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.com.mcp.application.api.request.McpRequest;
import dev.com.mcp.application.api.response.McpResponse;
import dev.com.mcp.application.services.McpOrchestrator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class McpApiController {

    private final McpOrchestrator orchestrator;

    public McpApiController(McpOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping("/mcp")
    public ResponseEntity<McpResponse> handle(@Valid @RequestBody McpRequest request) {
        McpResponse response = orchestrator.handle(request.getInput());
        return ResponseEntity.ok(response);
    }
}
