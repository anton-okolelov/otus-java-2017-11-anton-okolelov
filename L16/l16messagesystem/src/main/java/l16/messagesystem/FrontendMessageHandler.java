package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;

import java.util.logging.Logger;

public class FrontendMessageHandler extends MessageHandler {
    static Logger logger = Logger.getLogger(FrontendMessageHandler.class.getName());
    private final Queue queue;

    public FrontendMessageHandler(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void onMessageRecieved(Message message) {
        logger.info("Message");
        queue.add(message);
    }
}
