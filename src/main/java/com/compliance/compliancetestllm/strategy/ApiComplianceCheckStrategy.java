package com.compliance.compliancetestllm.strategy;

import com.compliance.compliancetestllm.client.OpenAIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiComplianceCheckStrategy implements ComplianceCheckStrategy {

    private final OpenAIClient openAIClient;
    private final String compliancePolicy;
    private static final String PROMPT_TEMPLATE = "Check webpage content against the compliance policy: %s\n\nContent: %s";

    @Autowired
    public ApiComplianceCheckStrategy(OpenAIClient openAIClient, @Value("${compliance.policy}") String compliancePolicy) {
        this.openAIClient = openAIClient;
        this.compliancePolicy = compliancePolicy;
    }

    @Override
    public void performCheck() {
        String contentToCheck = "Sample content to check";  // Replace with actual content as needed
        String prompt = String.format(PROMPT_TEMPLATE, compliancePolicy, contentToCheck);
        String response = openAIClient.getCompletion(prompt);
        System.out.println("Compliance check response: " + response);
    }
}

