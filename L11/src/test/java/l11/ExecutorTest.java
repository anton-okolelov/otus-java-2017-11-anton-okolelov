package l11;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ExecutorTest {

    private Executor executor;

    @Before
    public void setUp() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/l09?user=anton&password=12345&ssl=false";
        ConnectionManager connectionManager = new ConnectionManager(url);
        Connection connection = connectionManager.getConnection();
        connection.createStatement().execute("DROP TABLE IF EXISTS \"user\"");
        connection.createStatement().execute(
                "CREATE TABLE \"user\" " +
                "( " +
                "  id BIGSERIAL, " +
                "  name VARCHAR(255), " +
                "  age INT, " +
                "  PRIMARY KEY (id) " +
                ")"
        );

        executor = new Executor(connection, new MyCacheEngine());
    }

    @Test
    public void saveNewUser() {
        UserDataSet user = new UserDataSet();
        user.setName("Ваня");
        user.setAge(15);

        executor.save(user);
        UserDataSet result = executor.load(1, UserDataSet.class);
        assertEquals(15, result.getAge());
        assertEquals("Ваня", result.getName());
    }

    @Test
    public void updateUser() {
        UserDataSet user = new UserDataSet();
        user.setName("Ваня");
        user.setAge(15);
        executor.save(user);

        user = executor.load(1, UserDataSet.class);
        user.setAge(16);
        executor.save(user);
        UserDataSet result = executor.load(1, UserDataSet.class);
        assertEquals(16, result.getAge());
    }
}
