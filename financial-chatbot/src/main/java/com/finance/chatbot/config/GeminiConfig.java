package com.finance.chatbot.config;

import com.google.ai.client.generativeai.GenerativeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.model}")
    private String modelName;

    @Bean
    public GenerativeModel generativeModel() {
        return new GenerativeModel(modelName, apiKey);
    }
}
