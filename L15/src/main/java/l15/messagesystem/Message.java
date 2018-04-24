package l15.messagesystem;

public abstract class Message {
    private final QueueId queueIdToResponse;

    public Message(QueueId queueIdToResponse) {
        this.queueIdToResponse = queueIdToResponse;
    }
}
