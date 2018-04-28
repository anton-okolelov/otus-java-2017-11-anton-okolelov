package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;

import java.util.logging.Logger;

public class DbMessageHandler extends MessageHandler {
    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void onMessageRecieved(Message message) {
        logger.info("Message");
    }
}
