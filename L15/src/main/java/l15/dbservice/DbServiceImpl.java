package l15.dbservice;

import l15.application.MessageSystemContext;
import l15.application.MessageToDb;
import l15.application.MessageToFrontend;
import l15.messagesystem.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbServiceImpl implements DbService {

    private final UserRepository userRepository;

    @Autowired
    public DbServiceImpl(MessageSystemContext messageSystemContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        messageSystemContext.getDbQueue().addHandler((MessageToDb messageToDb) -> {
            User user = this.getUser(messageToDb.getLogin());
            Queue frontendQueue = messageSystemContext.getFrontendQueue();
            Long userId = user != null ? user.getId() : null;

            MessageToFrontend messageToFrontend = new MessageToFrontend(frontendQueue.getQueueId(), messageToDb.getSession(), userId);
            messageSystemContext.getFrontendQueue().postMessage(messageToFrontend);
        });
    }

    @Override
    public User getUser(String login) {
        return userRepository.findOneByLogin(login);
    }


}
