package l16.messagesystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10000);
        serverSocket.accept();
    }
}
