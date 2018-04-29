package l16.dbservice;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User findOneByLogin(String login);
}
