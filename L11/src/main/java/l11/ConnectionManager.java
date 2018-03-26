package l11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final String url;

    public ConnectionManager(String url) {
        this.url = url;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
