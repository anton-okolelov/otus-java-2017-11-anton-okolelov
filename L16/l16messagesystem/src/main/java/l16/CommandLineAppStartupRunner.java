package l16;

import l16.common.socket.MessageToDb;
import l16.common.socket.MessageToFrontend;
import l16.messagesystem.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    static Logger logger = Logger.getLogger(CommandLineAppStartupRunner.class.getName());
    private final MessageSystemContext messageSystemContext;

    private final int FRONTEND_PORT = 8124;
    private final int BACKEND_PORT = 8123;

    public CommandLineAppStartupRunner(MessageSystemContext context) {
        this.messageSystemContext = context;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("1");
        Server server = new Server();
        logger.info("2");
        Queue frontendQueue = messageSystemContext.getFrontendQueue();
        Queue dbQueue = messageSystemContext.getDbQueue();
        server.startListening(BACKEND_PORT, new DbMessageHandler(frontendQueue), MessageToFrontend.class, dbQueue);
        server.startListening(FRONTEND_PORT, new FrontendMessageHandler(dbQueue), MessageToDb.class, frontendQueue);
        WorkerProcessesRunner processesRunner = new WorkerProcessesRunner();
        processesRunner.runDbService();
        processesRunner.runFrontend();

    }
}
