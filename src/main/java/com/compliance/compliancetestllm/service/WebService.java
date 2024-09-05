package com.compliance.compliancetestllm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import java.io.IOException;

@Service
public class WebService {

    private final RestTemplate restTemplate;
    private String url;

    public WebService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String fetch() throws IOException {
        // Fetching the webpage content
        return restTemplate.getForObject(url, String.class);
    }

    public String extractText(String html) {
        // Extracting text using Jsoup
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }
}
