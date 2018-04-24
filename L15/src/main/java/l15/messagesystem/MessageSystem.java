package l15.messagesystem;

import java.util.HashMap;
import java.util.Map;

public class MessageSystem {
    private Map<QueueId, Queue> queues = new HashMap<>();


    public MessageSystem() {

    }

    public Queue registerQueue(QueueId queueId) {
        Queue queue = new Queue(queueId);
        queues.put(queueId, queue);
        queue.startProcessing();
        return queue;
    }

}
