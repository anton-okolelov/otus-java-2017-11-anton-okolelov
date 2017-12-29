package l05.myunit;

public class FailInfo {
    private String method;
    private String message;

    public FailInfo(String method, String message) {
        this.message = message;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }
}
