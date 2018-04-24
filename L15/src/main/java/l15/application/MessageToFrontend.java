package l15.application;

import l15.messagesystem.Message;
import l15.messagesystem.QueueId;
import org.springframework.web.socket.WebSocketSession;

public class MessageToFrontend extends Message {

    private final WebSocketSession session;
    private final Long userId;

    public MessageToFrontend(QueueId queueIdToResponse, WebSocketSession session, Long userId) {
        super(queueIdToResponse);
        this.session = session;
        this.userId = userId;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public Long getUserId() {
        return userId;
    }
}
