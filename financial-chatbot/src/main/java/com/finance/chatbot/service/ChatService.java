package com.finance.chatbot.service;

import com.finance.chatbot.dto.ChatRequest;
import com.finance.chatbot.dto.ChatResponse;

public interface ChatService {
    ChatResponse processMessage(ChatRequest request);
}
