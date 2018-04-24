package l15.application;

import l15.frontend.WebSocketsHandler;
import l15.messagesystem.MessageSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class AppConfig {

    @Bean
    MessageSystemContext messageSystemContext() {
        MessageSystem messageSystem = new MessageSystem();
        MessageSystemContext context = new MessageSystemContext(messageSystem);
        return context;
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
