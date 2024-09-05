package com.compliance.compliancetestllm.client;

import com.compliance.compliancetestllm.dto.ChatRequest;
import com.compliance.compliancetestllm.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BasicOpenAIClient implements OpenAIClient {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Override
    public String getCompletion(String prompt) {
        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        System.out.println(restTemplate.postForObject(apiUrl, request, String.class));

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().content();
    }
}
