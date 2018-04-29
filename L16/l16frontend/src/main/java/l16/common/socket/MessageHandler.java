package l16.common.socket;

public abstract class MessageHandler {
    protected SocketMessagesManager socketMessagesManager;

    public abstract void onMessageRecieved(Message message);

    public void setSocketMessagesManager(SocketMessagesManager socketMessagesManager) {
        this.socketMessagesManager = socketMessagesManager;
    }
}
