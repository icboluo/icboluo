package com.icboluo.websocket;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 股票 WebSocket 处理器
 * 推送行情变化和交易事件
 */
@Slf4j
@Component
public class StockWebSocketHandler extends TextWebSocketHandler {
    /**
     * seasonId -> Set<WebSocketSession>
     */
    private final ConcurrentHashMap<Integer, Set<WebSocketSession>> seasonSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer seasonId = extractSeasonId(session);
        if (seasonId != null) {
            seasonSessions.computeIfAbsent(seasonId, k -> ConcurrentHashMap.newKeySet()).add(session);
            log.info("WebSocket 连接建立: seasonId={}, sessionId={}", seasonId, session.getId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Integer seasonId = extractSeasonId(session);
        if (seasonId != null) {
            Set<WebSocketSession> sessions = seasonSessions.get(seasonId);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    seasonSessions.remove(seasonId);
                }
            }
            log.info("WebSocket 连接关闭: seasonId={}, sessionId={}", seasonId, session.getId());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject json = JSON.parseObject(payload);
        // 心跳 ping/pong
        if ("ping".equals(json.getString("type"))) {
            session.sendMessage(new TextMessage("{\"type\":\"pong\"}"));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket 传输错误: sessionId={}", session.getId(), exception);
        if (session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }

    /**
     * 向指定赛季的所有连接推送消息
     *
     * @param seasonId 赛季ID
     * @param type     消息类型（quote/trade）
     * @param data     消息数据
     */
    public void broadcastToSeason(Integer seasonId, String type, Object data) {
        Set<WebSocketSession> sessions = seasonSessions.get(seasonId);
        if (sessions == null || sessions.isEmpty()) {
            return;
        }
        JSONObject message = new JSONObject();
        message.put("type", type);
        message.put("data", data);
        TextMessage textMessage = new TextMessage(message.toJSONString());
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(textMessage);
                } catch (IOException e) {
                    log.error("WebSocket 推送失败: sessionId={}", session.getId(), e);
                }
            }
        }
    }

    //从 WebSocket URI 中提取 seasonId
    private Integer extractSeasonId(WebSocketSession session) {
        String uri = session.getUri().getPath();
        String[] parts = uri.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            if ("stock".equals(parts[i]) && i + 1 < parts.length) {
                try {
                    return Integer.parseInt(parts[i + 1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
