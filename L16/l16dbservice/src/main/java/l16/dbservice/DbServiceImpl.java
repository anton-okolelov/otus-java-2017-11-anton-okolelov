package l16.dbservice;

import org.springframework.stereotype.Component;

@Component
public class DbServiceImpl implements DbService {

    private final UserRepository userRepository;

    public DbServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String login) {
        return userRepository.findOneByLogin(login);
    }


}
