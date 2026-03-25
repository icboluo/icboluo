package com.icboluo.websocket;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.icboluo.entity.CultivationCareer;
import com.icboluo.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 游戏 WebSocket 处理器
 * 用于推送玩家的修仙生涯实时数据
 *
 * @author icboluo
 * @since 2026-03-25 21:45
 */
@Slf4j
public class GameWebSocketHandler extends TextWebSocketHandler {

    /**
     * 玩家会话存储，key: playerId, value: WebSocketSession
     */
    private static final Map<Integer, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer playerId = extractPlayerId(session);
        if (playerId != null) {
            SESSIONS.put(playerId, session);
            log.info("WebSocket connected: playerId={}", playerId);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Integer playerId = extractPlayerId(session);
        if (playerId != null) {
            SESSIONS.remove(playerId);
            log.info("WebSocket disconnected: playerId={}", playerId);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 可以处理前端发送的消息（如心跳、订阅等）
        String payload = message.getPayload();
        JSONObject json = JSON.parseObject(payload);
        String type = json.getString("type");
        if ("ping".equals(type)) {
            session.sendMessage(new TextMessage("{\"type\":\"pong\"}"));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws
            Exception {
        Integer playerId = extractPlayerId(session);
        if (playerId != null) {
            SESSIONS.remove(playerId);
        }
        if (session.isOpen()) {
            session.close();
        }
        log.error("WebSocket transport error: playerId={}", playerId, exception);
    }

    /*
     *推送玩家生涯数据到指定玩家（同时推送玩家信息供前端更新）
     *@param playerId 玩家ID
     *@param data 生涯数据
     *@param player 玩家数据（可选，为空则不推送玩家信息）
     */
    public static void pushCareerData(Integer playerId, CultivationCareer data, Player
            player) {
        WebSocketSession session = SESSIONS.get(playerId);
        if (session != null && session.isOpen()) {
            try {
                JSONObject message = new JSONObject();
                message.put("type", "career_update");
                message.put("data", JSON.toJSON(data));
                // 如果提供了玩家信息，一并推送给前端
                if (player != null) {
                    JSONObject playerData = new JSONObject();
                    playerData.put("id", player.getId());
                    playerData.put("age", player.getAge());
                    playerData.put("attack", player.getAttack());
                    playerData.put("blood", player.getBlood());
                    playerData.put("maxBlood", player.getMaxBlood());
                    playerData.put("experience", player.getExperience());
                    playerData.put("totalExperience", player.getTotalExperience());
                    playerData.put("level", player.getLevel());
                    playerData.put("name", player.getName());
                    message.put("player", playerData);
                }
                session.sendMessage(new TextMessage(message.toJSONString()));
                log.debug("Pushed career data to playerId={}: {}", playerId, data.getOper());
            } catch (
                    IOException e) {
                log.error("Failed to push career data to playerId={}", playerId, e);
                SESSIONS.remove(playerId);
            }
        }
    }

    /**
     * 推送玩家生涯数据到指定玩家（兼容旧调用）
     *
     * @param playerId 玩家ID
     * @param data     生涯数据
     */
    public static void pushCareerData(Integer playerId, CultivationCareer data) {
        pushCareerData(playerId, data, null);
    }

    /**
     * 从 URI 中提取玩家ID
     *
     * @param session
     * @return
     */
    private Integer extractPlayerId(WebSocketSession session) {
        try {
            String uri = session.getUri().toString();
            String playerIdStr = uri.substring(uri.lastIndexOf("/") + 1);
            return Integer.parseInt(playerIdStr);
        } catch (Exception e) {
            log.error("Failed to extract playerId from URI: {}", session.getUri(), e);
            return null;
        }
    }

    /**
     * 获取当前在线玩家数量
     *
     * @return
     */
    public static int getOnlineCount() {
        return SESSIONS.size();
    }
}
