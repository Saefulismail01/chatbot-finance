package com.finance.chatbot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequest {
    @NotBlank(message = "Message cannot be empty")
    private String message;
    
    private String userId;
    private String sessionId;
}
