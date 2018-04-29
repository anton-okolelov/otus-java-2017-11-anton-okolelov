package l16.common.socket;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketMessagesManager {
    private final Socket socket;
    private final Gson gson;

    public SocketMessagesManager(Socket socket){
        this.socket = socket;
        gson = new Gson();
    }

    public void sendMessage(Message message) {
        String json = gson.toJson(message);
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void waitMessages(MessageHandler handler, Class<? extends Message> klass) {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String json = reader.readLine().trim();
                handler.onMessageRecieved(gson.fromJson(json, klass));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
