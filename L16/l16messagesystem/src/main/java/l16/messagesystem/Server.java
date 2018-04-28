package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;
import l16.common.socket.SocketMessagesManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    @SuppressWarnings("InfiniteLoopStatement")
    public void startListening(int port, MessageHandler handler, Class<? extends Message> klass) {
        new Thread(() -> {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    Socket socket = serverSocket.accept();
                    SocketMessagesManager socketMessagesManager = new SocketMessagesManager(socket);
                    handler.setSocketMessagesManager(socketMessagesManager);
                    socketMessagesManager.waitMessages(handler, klass);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
