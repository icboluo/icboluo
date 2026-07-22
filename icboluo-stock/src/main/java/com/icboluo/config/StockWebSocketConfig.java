package com.icboluo.config;

import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class StockWebSocketConfig implements WebSocketConfigurer {
    private final StockWebSocketHandler stockWebSocketHandler;

    public StockWebSocketConfig(StockWebSocketHandler stockWebSocketHandler) {
        this.stockWebSocketHandler = stockWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockWebSocketHandler, "/ws/stock/{seasonId}").setAllowedOrigins("*");
    }
}
