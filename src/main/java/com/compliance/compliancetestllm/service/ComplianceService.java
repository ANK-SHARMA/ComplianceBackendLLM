package com.compliance.compliancetestllm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ComplianceService {

    private final OpenAIClient openAIClient;
    private final String compliancePolicy;
    private final static String GENERIC_PROMPT_TEMP = "Check webpage content against the compliance policy: %s\n\nContent: %s";

    @Autowired
    public ComplianceService(OpenAIClient openAIClient, @Value("${compliance.policy}") String compliancePolicy) {
        this.openAIClient = openAIClient;
        this.compliancePolicy = compliancePolicy;
    }
    public String checkCompliance(String text) {
        try {
            return openAIClient.getCompletion(getPrompt(text));
        } catch (RuntimeException e) {
            throw new RuntimeException("An unexpected error occurred during compliance checking: " + e.getMessage(), e);
        }
    }
    private String getPrompt(String text) {
        return String.format(GENERIC_PROMPT_TEMP, compliancePolicy, text);
    }
}
