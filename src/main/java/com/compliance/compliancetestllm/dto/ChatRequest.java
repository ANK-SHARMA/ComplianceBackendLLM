package com.compliance.compliancetestllm.dto;

import java.util.List;

public record ChatRequest(String model, List<Message> messages) {

    public ChatRequest(String model, String prompt) {
        this(model, List.of(
            new Message("system", "Check webpage content against the compliance policy"),
            new Message("user", prompt)
        ));
    }
}
