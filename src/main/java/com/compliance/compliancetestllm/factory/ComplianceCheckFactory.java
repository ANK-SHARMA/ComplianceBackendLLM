package com.compliance.compliancetestllm.factory;

import com.compliance.compliancetestllm.strategy.ApiComplianceCheckStrategy;
import com.compliance.compliancetestllm.strategy.ComplianceCheckStrategy;
import com.compliance.compliancetestllm.strategy.DatabaseComplianceCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ComplianceCheckFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public ComplianceCheckFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ComplianceCheckStrategy getComplianceCheck(String checkType) {
        switch (checkType) {
            case "api":
                return applicationContext.getBean(ApiComplianceCheckStrategy.class);
            case "db":
                return applicationContext.getBean(DatabaseComplianceCheckStrategy.class);
            default:
                throw new UnsupportedOperationException("Unknown compliance check type");
        }
    }
}
