package com.compliance.compliancetestllm.service;

import com.compliance.compliancetestllm.client.OpenAIClient;
import com.compliance.compliancetestllm.factory.ComplianceCheckFactory;
import com.compliance.compliancetestllm.strategy.ComplianceCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ComplianceService {

    private final OpenAIClient openAIClient;
    private final String compliancePolicy;
    private final ComplianceCheckFactory complianceCheckFactory;
    private static final String GENERIC_PROMPT_TEMP = "Check webpage content against the compliance policy: %s\n\nContent: %s";

    @Autowired
    public ComplianceService(OpenAIClient openAIClient, @Value("${compliance.policy}") String compliancePolicy, ComplianceCheckFactory complianceCheckFactory) {
        this.openAIClient = openAIClient;
        this.compliancePolicy = compliancePolicy;
        this.complianceCheckFactory = complianceCheckFactory;
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

    // Integrating Strategy Pattern for Compliance Check
    public void performComplianceCheck(String checkType) {
        ComplianceCheckStrategy strategy = complianceCheckFactory.getComplianceCheck(checkType);
        strategy.performCheck();  // Delegates the compliance check to the correct strategy
    }
}
