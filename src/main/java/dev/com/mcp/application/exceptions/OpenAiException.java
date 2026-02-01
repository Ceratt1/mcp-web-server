package dev.com.mcp.application.exceptions;

public class OpenAiException extends RuntimeException {

    private final int statusCode;
    private final String responseBody;

    public OpenAiException(int statusCode, String responseBody) {
        super("OpenAI error " + statusCode);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
