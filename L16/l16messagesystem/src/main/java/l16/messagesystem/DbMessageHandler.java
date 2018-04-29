package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;

import java.util.logging.Logger;

public class DbMessageHandler extends MessageHandler {
    private final Queue queue;
    Logger logger = Logger.getLogger(getClass().getName());

    public DbMessageHandler(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void onMessageRecieved(Message message)
    {
        logger.info("Message");
        queue.add(message);
    }
}
