package l16.common.socket;

public class MessageToFrontend implements Message {
    private String socketSessionId;
    private Long userId;

    public void setSocketSessionId(String socketSessionId) {
        this.socketSessionId = socketSessionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
