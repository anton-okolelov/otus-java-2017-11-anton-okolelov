package l08;

import l08.cases.Empty;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class MyJsonSerializer {
    public String toJson(Object object) {
        Field[] fields = object.getClass().getFields();

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        for (Field field : fields) {

            Class<?> type = field.getType();
            try {

                if (type.getName().equals("int")) {
                    jsonObjectBuilder.add(field.getName(), field.getInt(object));
                }

                if (type.getName().endsWith("[I")) {
                    Object value = field.get(object);
                    jsonObjectBuilder.add(field.getName(), new Js);
                }


            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        return jsonObjectBuilder.build().toString();
    }
}
