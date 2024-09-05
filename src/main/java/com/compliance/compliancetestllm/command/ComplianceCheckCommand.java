package com.compliance.compliancetestllm.command;

import com.compliance.compliancetestllm.service.ComplianceService;

public class ComplianceCheckCommand implements Command {
    private ComplianceService complianceService;
    private String checkType;

    public ComplianceCheckCommand(ComplianceService service, String checkType) {
        this.complianceService = service;
        this.checkType = checkType;
    }

    @Override
    public void execute() {
        complianceService.performComplianceCheck(checkType);  // Executes the compliance check
    }
}

