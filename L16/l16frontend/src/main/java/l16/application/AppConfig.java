package l16.application;

import l16.common.socket.SocketMessagesManager;
import l16.frontend.WebSocketsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class AppConfig {

    @Bean
    SocketMessagesManager socketMessagesManager() {
        try {
            Socket socket = new Socket("localhost", 8124);
            return new SocketMessagesManager(socket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Configuration
    @EnableWebSocket
    public class AppWebSocketConfig implements WebSocketConfigurer {

        @Autowired
        private WebSocketsHandler webSocketsHandler;

        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

            registry.addHandler(this.webSocketsHandler, "/ws").setAllowedOrigins("*");
        }

    }


}
