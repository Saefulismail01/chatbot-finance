package com.finance.chatbot.service.impl;

import com.finance.chatbot.dto.ChatRequest;
import com.finance.chatbot.dto.ChatResponse;
import com.finance.chatbot.service.ChatService;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiChatServiceImpl implements ChatService {

    private final GenerativeModel generativeModel;

    @Override
    public ChatResponse processMessage(ChatRequest request) {
        try {
            String prompt = "You are a helpful financial assistant. Provide concise and accurate financial advice. " +
                    "If the question is not related to finance, politely decline to answer.\n\n" +
                    "User: " + request.getMessage() + "\nAssistant:";

            GenerateContentResponse response = generativeModel.generateContent(prompt);
            String aiResponse = ResponseHandler.getText(response);

            return ChatResponse.builder()
                    .response(aiResponse)
                    .sessionId(request.getSessionId() != null ? request.getSessionId() : generateSessionId())
                    .status("SUCCESS")
                    .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                    .build();
        } catch (Exception e) {
            log.error("Error processing chat request: {}", e.getMessage(), e);
            return ChatResponse.builder()
                    .response("Sorry, I encountered an error while processing your request.")
                    .sessionId(request.getSessionId() != null ? request.getSessionId() : generateSessionId())
                    .status("ERROR")
                    .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                    .build();
        }
    }

    private String generateSessionId() {
        return "sess_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
