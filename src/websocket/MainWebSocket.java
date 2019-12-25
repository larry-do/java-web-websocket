package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/actions")
public class MainWebSocket {

    private SessionHandler sessionHandler = new SessionHandler();

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
        System.out.println("Session opened: " + session.getId());
    }


    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
        System.out.println("Session closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error: " + error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("Message from session [" + session.getId() + "]: " + message);
        session.getBasicRemote().sendText("Hello, Client");
    }
}
