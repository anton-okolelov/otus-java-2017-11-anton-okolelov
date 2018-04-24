package l15.application;

import l15.messagesystem.Message;
import l15.messagesystem.QueueId;
import org.springframework.web.socket.WebSocketSession;

public class MessageToDb extends Message {

    private final String login;
    private WebSocketSession session;

    public MessageToDb(QueueId queueIdToResponse, String login, WebSocketSession session) {
        super(queueIdToResponse);
        this.login = login;
        this.session = session;
    }

    public String getLogin() {
        return login;
    }

    public WebSocketSession getSession() {
        return session;
    }
}
