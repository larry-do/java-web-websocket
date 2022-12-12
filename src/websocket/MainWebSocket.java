package websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/actions")
public class MainWebSocket {

    private SessionHandler sessionHandler = new SessionHandler();

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
        System.out.println("Opened session ID: " + session.getId());
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
        System.out.println("Closed session ID: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error: " + error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("Message from session with ID " + session.getId() + ": " + message);
        session.getBasicRemote().sendText("Hello, Client");
    }
}
