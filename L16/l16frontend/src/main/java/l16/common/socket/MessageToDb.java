package l16.common.socket;

public class MessageToDb implements Message {
    private String login;
    private String socketSessionId;

    public String getLogin() {
        return login;
    }

    public String getSocketSessionId() {
        return socketSessionId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSocketSessionId(String socketSessionId) {
        this.socketSessionId = socketSessionId;
    }
}
