package l16.frontend;

import l16.common.socket.SocketMessagesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class WebSocketsHandler extends TextWebSocketHandler {
    private final Logger logger = Logger.getLogger(WebSocketsHandler.class.getName());
    private final SocketMessagesManager socketMessagesManager;


    @Autowired
    public WebSocketsHandler(SocketMessagesManager socketMessagesManager) {
        this.socketMessagesManager = socketMessagesManager;
        
        /**
        this.messageSystemContext = messageSystemContext;
        messageSystemContext.getFrontendQueue().addHandler((MessageToFrontend message) -> {
            try {
                String text = "Unknown id";
                if (message.getUserId() != null) {
                    text = "Id: " + Long.toString(message.getUserId());
                }
                message.getSession().sendMessage(new TextMessage(text));
            } catch (IOException e) {
                logger.info("Couldn't send message to websocket");
            }
        });
         **/
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Connection Established");
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage websocketMessage) throws Exception {
        super.handleTextMessage(session, websocketMessage);
/*        String login = websocketMessage.getPayload();
        logger.info("Recieved message: " + login);
        QueueId queueId = messageSystemContext.getFrontendQueue().getQueueId();
        MessageToDb message = new MessageToDb(queueId, login, session);
        messageSystemContext.getDbQueue().postMessage(message);*/
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Connection Closed");
    }

}
