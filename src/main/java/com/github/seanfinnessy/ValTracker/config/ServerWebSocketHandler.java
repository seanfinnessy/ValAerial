package com.github.seanfinnessy.ValTracker.config;

import com.github.seanfinnessy.ValTracker.entity.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

public class ServerWebSocketHandler extends TextWebSocketHandler {
    Logger logger = Logger.getLogger(ServerWebSocketHandler.class.getName());
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private String prevSessionLoopState;

    private final UserSession userSession;

    @Autowired
    public ServerWebSocketHandler(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        logger.info("connected with the websocket client : " + session.getId());
        sessions.add(session);
        session.sendMessage(new TextMessage("One time message from server."));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("connection closed from the websocket client : " + session.getId());
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("pong"));
    }

    @Scheduled(fixedRate = 5000)
    void sendUpdatedUserSessionInfo() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {

                // check if previous session state is different from current.
                if (!userSession.getSessionLoopState().equalsIgnoreCase(prevSessionLoopState)) {

                    // send updated game state
                    session.sendMessage(new TextMessage("Game state changed to: " + userSession.getSessionLoopState()));

                    // update prev game state
                    prevSessionLoopState = userSession.getSessionLoopState();

                } else logger.info("Game state has not yet changed...");
            }
        }
    }

    private void sessionLoopStateHandler(String loopState) {
        switch (loopState) {
            case "MENUS" -> {
                System.out.println("MENUS");
            }
            case "PREGAME" -> {
                System.out.println("PREGAME");
            }
            case "INGAME" -> {
                System.out.println("INGAME");
            }
        }
    }
}