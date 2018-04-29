package l16.messagesystem;

import l16.common.socket.Message;
import l16.common.socket.MessageHandler;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;


public class Queue {
    ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();

    public void add(Message message) {
        messages.add(message);
    }

    public Message consume() {
        return this.messages.poll();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void addConsumer(Consumer<Message> consumer) {
        new Thread(() -> {
            while (true) {
                if (messages.isEmpty()) {
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Message message = messages.poll();
                if (message != null) {
                    consumer.accept(message);
                }
            }
        }).start();
    }
}
