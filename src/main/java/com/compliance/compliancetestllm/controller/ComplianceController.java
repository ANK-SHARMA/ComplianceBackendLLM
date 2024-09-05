package com.compliance.compliancetestllm.controller;

import com.compliance.compliancetestllm.service.ComplianceService;
import com.compliance.compliancetestllm.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@Service
public class ComplianceController {

    private final WebService webService;
    private final ComplianceService complianceService;

    @Autowired
    public ComplianceController(WebService webService, ComplianceService complianceService) {
        this.webService = webService;
        this.complianceService = complianceService;
    }

    @GetMapping("/")
    public String index() {
        return "Service is Up";
    }

    @GetMapping("/compliance/check")
    public ResponseEntity<String> checkCompliance(@RequestParam String url) {
        try {
            webService.setUrl(url);
            String htmlContent = webService.fetch();
            String textContent = webService.extractText(htmlContent);
            return ResponseEntity.ok(complianceService.checkCompliance(textContent));
        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request: " + e.getMessage());
        }
    }
}