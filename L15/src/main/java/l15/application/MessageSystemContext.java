package l15.application;

import l15.messagesystem.MessageSystem;
import l15.messagesystem.Queue;
import l15.messagesystem.QueueId;

public class MessageSystemContext {
    private final MessageSystem messageSystem;
    private final Queue frontendQueue;
    private final Queue dbQueue;

    public MessageSystemContext(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.frontendQueue = messageSystem.registerQueue(new QueueId("frontend"));
        this.dbQueue = messageSystem.registerQueue(new QueueId("db"));
    }

    public Queue getDbQueue() {
        return dbQueue;
    }

    public Queue getFrontendQueue() {
        return frontendQueue;
    }
}
