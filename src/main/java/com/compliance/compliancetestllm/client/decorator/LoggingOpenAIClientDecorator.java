package com.compliance.compliancetestllm.client.decorator;

import com.compliance.compliancetestllm.client.OpenAIClient;

public class LoggingOpenAIClientDecorator implements OpenAIClient {
    private OpenAIClient decoratedClient;

    public LoggingOpenAIClientDecorator(OpenAIClient client) {
        this.decoratedClient = client;
    }

    @Override
    public String getCompletion(String prompt) {
        System.out.println("Logging request with prompt: " + prompt);
        String response = decoratedClient.getCompletion(prompt);
        System.out.println("Response received: " + response);
        return response;
    }
}

