package l15.messagesystem;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class Queue {

    private final QueueId queueId;
    private final List<Consumer<? extends Message>> handlers = new ArrayList<>();

    public ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();

    public Queue(QueueId queueId) {
        this.queueId = queueId;
    }

    public void postMessage(Message message) {
        messages.add(message);
    }

    @Nullable
    public Message getMessage() {
        return messages.poll();
    }

    public QueueId getQueueId() {
        return queueId;
    }

    public void addHandler(Consumer<? extends Message> handler) {
        handlers.add(handler);
    }

    public void startProcessing() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (messages.isEmpty() || handlers.isEmpty()) {
                        Thread.sleep(10);
                        continue;
                    }
                    Message message = messages.poll();
                    for (Consumer handler : handlers) {
                        handler.accept(message);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        thread.start();
    }
}
