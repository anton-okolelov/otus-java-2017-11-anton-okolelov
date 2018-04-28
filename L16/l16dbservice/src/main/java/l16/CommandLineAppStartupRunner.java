package l16;

import l16.common.socket.MessageToDb;
import l16.common.socket.SocketMessagesManager;
import l16.dbservice.DbMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.logging.Logger;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger(CommandLineAppStartupRunner.class.getName());
    private final DbMessageHandler handler;


    @Autowired
    public CommandLineAppStartupRunner(DbMessageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started");
        int port = 8123;
        logger.info("Connecting to port " + port + "...");

        Socket socket = new Socket("localhost", port);
        SocketMessagesManager socketMessagesManager = new SocketMessagesManager(socket);
        socketMessagesManager.waitMessages(handler, MessageToDb.class);
    }

}
