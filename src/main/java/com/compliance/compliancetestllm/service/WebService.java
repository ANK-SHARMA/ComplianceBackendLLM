package com.compliance.compliancetestllm.service;

import com.compliance.compliancetestllm.client.BasicOpenAIClient;
import com.compliance.compliancetestllm.client.OpenAIClient;
import com.compliance.compliancetestllm.client.decorator.LoggingOpenAIClientDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import java.io.IOException;

@Service
public class WebService {

    private final RestTemplate restTemplate;
    private String url;

    private OpenAIClient openAIClient;

    @Autowired
    public WebService(RestTemplate restTemplate, BasicOpenAIClient basicOpenAIClient) {
        this.restTemplate = restTemplate;
        this.openAIClient = new LoggingOpenAIClientDecorator(basicOpenAIClient);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String fetch() throws IOException {
        return restTemplate.getForObject(url, String.class);
    }

    public String extractText(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    public String getOpenAICompletion(String prompt) {
        return openAIClient.getCompletion(prompt);
    }
}