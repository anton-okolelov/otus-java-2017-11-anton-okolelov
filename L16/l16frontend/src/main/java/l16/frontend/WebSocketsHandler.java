package l16.frontend;

import l16.common.socket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Component
public class WebSocketsHandler extends TextWebSocketHandler {
    private final Logger logger = Logger.getLogger(WebSocketsHandler.class.getName());
    private final SocketMessagesManager socketMessagesManager;

    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    @Autowired
    public WebSocketsHandler(SocketMessagesManager socketMessagesManager) {
        this.socketMessagesManager = socketMessagesManager;
        FrontendSocketMessageHandler handler = new FrontendSocketMessageHandler(this);
        new Thread(()->this.socketMessagesManager.waitMessages(handler, MessageToFrontend.class)).start();
    }

    class FrontendSocketMessageHandler extends MessageHandler {

        private final WebSocketsHandler handler;

        public FrontendSocketMessageHandler(WebSocketsHandler handler) {
            this.handler = handler;
        }

        @Override
        public void onMessageRecieved(Message message) {
            MessageToFrontend frontendMessage = (MessageToFrontend) message;

            WebSocketSession session = handler.sessions.get(frontendMessage.getSocketSessionId());
            if (session == null) {
                return;
            }
            String text = "Unknown id";
            if (frontendMessage.getUserId() != null) {
                text = "Id: " + Long.toString(frontendMessage.getUserId());
            }
            try {
                session.sendMessage(new TextMessage(text));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Connection Established");
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage websocketMessage) throws Exception {
        super.handleTextMessage(session, websocketMessage);
        String login = websocketMessage.getPayload();
        logger.info("Recieved message: " + login);
        MessageToDb messageToDb = new MessageToDb();
        messageToDb.setLogin(login);
        messageToDb.setSocketSessionId(session.getId());
        sessions.put(session.getId(), session);
        socketMessagesManager.sendMessage(messageToDb);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Connection Closed");
        sessions.remove(session.getId());
    }

}
