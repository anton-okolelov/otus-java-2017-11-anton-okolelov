package l16.dbservice;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;
import l16.common.socket.MessageToDb;
import l16.common.socket.MessageToFrontend;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DbMessageHandler extends MessageHandler {
    private final DbService dbService;
    Logger logger = Logger.getLogger(DbMessageHandler.class.getName());

    public DbMessageHandler(DbServiceImpl dbService) {
        this.dbService = dbService;
    }
gi
    public void onMessageRecieved(Message message) {
        logger.info("Message recieved");
        MessageToDb messageToDb = (MessageToDb) message;
        User user = this.dbService.getUser(messageToDb.getLogin());
        MessageToFrontend messageToFrontend = new MessageToFrontend();
        messageToFrontend.setSocketSessionId(messageToDb.getSocketSessionId());
        messageToFrontend.setUserId(user != null ? user.getId() : null);
        this.socketMessagesManager.sendMessage(messageToFrontend);
    }
}
