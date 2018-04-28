package l16.messagesystem;

import l16.common.socket.MessageToFrontend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    static Logger logger = Logger.getLogger(CommandLineAppStartupRunner.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("1");
        Server server = new Server();
        logger.info("2");
        server.startListening(8123, new DbMessageHandler(), MessageToFrontend.class);
        logger.info("3");
        WorkerProcessesRunner processesRunner = new WorkerProcessesRunner();
        logger.info("4");
        processesRunner.runDbService();
        logger.info("5");
    }
}
