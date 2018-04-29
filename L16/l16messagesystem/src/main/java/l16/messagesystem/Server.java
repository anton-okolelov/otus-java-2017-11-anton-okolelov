package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;
import l16.common.socket.SocketMessagesManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    static Logger logger = Logger.getLogger(Server.class.getName());
    @SuppressWarnings("InfiniteLoopStatement")
    public void startListening(int port, MessageHandler handler, Class<? extends Message> klass, Queue queue) {
        new Thread(() -> {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    Socket socket = serverSocket.accept();
                    logger.info("accept");


                    SocketMessagesManager socketMessagesManager = new SocketMessagesManager(socket);
                    handler.setSocketMessagesManager(socketMessagesManager);

                    queue.addConsumer((Message message) -> {
                        logger.info("Consume");
                        socketMessagesManager.sendMessage(message);
                    });

                    socketMessagesManager.waitMessages(handler, klass);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
