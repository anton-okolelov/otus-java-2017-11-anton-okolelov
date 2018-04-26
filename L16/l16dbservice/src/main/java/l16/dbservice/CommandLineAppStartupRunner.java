package l16.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger(CommandLineAppStartupRunner.class.getName());
    private final DbService dbService;

    @Autowired
    public CommandLineAppStartupRunner(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started");

    }

}
