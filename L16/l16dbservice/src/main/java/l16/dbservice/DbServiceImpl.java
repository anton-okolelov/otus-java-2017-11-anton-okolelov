package l16.dbservice;

import org.springframework.stereotype.Component;

@Component
public class DbServiceImpl implements DbService {

    private final UserRepository userRepository;

    public DbServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    @Autowired
//    public DbServiceImpl(MessageSystemContext messageSystemContext, UserRepository userRepository) {
//        this.userRepository = userRepository;
//        messageSystemContext.getDbQueue().addHandler((MessageToDb messageToDb) -> {
//            User user = this.getUser(messageToDb.getLogin());
//            Queue frontendQueue = messageSystemContext.getFrontendQueue();
//            Long userId = user != null ? user.getId() : null;
//
//            MessageToFrontend messageToFrontend = new MessageToFrontend(frontendQueue.getQueueId(), messageToDb.getSession(), userId);
//            messageSystemContext.getFrontendQueue().postMessage(messageToFrontend);
//        });
//    }

    @Override
    public User getUser(String login) {
        return userRepository.findOneByLogin(login);
    }


}
