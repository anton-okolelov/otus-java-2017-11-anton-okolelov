package l11;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Executor {

    private final Connection connection;
    private final CacheEngine cache;

    Executor(Connection connection, CacheEngine cache) {
        this.connection = connection;
        this.cache = cache;
    }

    void save(DataSet dataSet) {

        if (dataSet.getId() == 0) {
            insertDataSet(dataSet);
        } else {
            updateDataSet(dataSet);
        }

    }

    private void updateDataSet(DataSet dataSet) {

        List<Object> values = new ArrayList<>();
        List<String> objectParts = new ArrayList<>();

        getData(dataSet).forEach((name, value) -> {
            values.add(value);
            objectParts.add(name + " = ?");
        });

        values.add(dataSet.getId());

        String sql = "UPDATE \"" + getTableName(dataSet.getClass()) + "\""
                + " SET " + String.join(",", objectParts)
                + " WHERE id = ?";


        executePreparedSql(sql, values);
    }


    private void insertDataSet(DataSet dataSet) {

        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        getData(dataSet).forEach((name, value) -> {
            names.add(name);
            values.add(value);
        });

        List<String> questionMarks = Collections.nCopies(values.size(), "?");

        String sql = "INSERT INTO \"" + getTableName(dataSet.getClass()) + "\"" +
                " ( " + String.join(", ", names) + ") " +
                " VALUES " +
                " (" + String.join(", ", questionMarks) + ") ";

        executePreparedSql(sql, values);
    }

    private void executePreparedSql(String sql, List<Object> values) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            int index = 0;
            for (Object value : values) {
                index++;
                if (value instanceof String) {
                    statement.setString(index, (String) value);
                } else if (value instanceof Integer) {
                    statement.setInt(index, (Integer) value);
                } else if (value instanceof Long) {
                    statement.setLong(index, (Long) value);
                }
            }

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    <T extends DataSet> T load(long id, Class<T> clazz) {

        Object cached = cache.get(id);
        if (cached != null) {
            return (T) cached;
        }

        Field[] fields = clazz.getDeclaredFields();

        List<String> names = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());

        String sql = "SELECT " + String.join(",", names)
                + " FROM \"" + getTableName(clazz) + "\" "
                + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            T result = clazz.getConstructor().newInstance();
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();


            result.setId(id);
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(result, resultSet.getObject(field.getName()));
            }

            cache.put(id, result);
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private String getTableName(Class<? extends DataSet> klass) {
        String className = klass.getSimpleName();
        return className
                .substring(0, className.length() - "DataSet".length())
                .toLowerCase();
    }


    private Map<String, Object> getData(DataSet dataSet) {
        Class<? extends DataSet> klass = dataSet.getClass();

        Map<String, Object> result = new HashMap<>();
        for (Field field : klass.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                result.put(field.getName(), field.get(dataSet));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
