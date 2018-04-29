package l16.messagesystem;

import org.springframework.stereotype.Component;

@Component
public class MessageSystemContext {
    private Queue dbQueue;
    private Queue frontendQueue;

    public MessageSystemContext(){
        this.dbQueue = new Queue();
        this.frontendQueue = new Queue();
    }

    public Queue getDbQueue(){
        return new Queue();
    }

    public Queue getFrontendQueue() {
        return new Queue();
    }
}
