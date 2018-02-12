package l08;


import javax.json.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MyJsonSerializer {

    public String toJsonString(Object object) {
        return toJson(object).toString();
    }


    private JsonValue toJson(Object object) {

        if (object instanceof Integer) {
            return Json.createValue((int) object);
        }

        if (object instanceof String) {
            return Json.createValue((String) object);
        }

        if (object instanceof Double) {
            return Json.createValue((Double) object);
        }

        if (object instanceof Collection) {
            return toJson((Collection) object).build();
        }

        if (object instanceof Map) {
            return toJson((Map) object).build();
        }

        Field[] fields = object.getClass().getDeclaredFields();

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                processField(object, jsonObjectBuilder, field);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        return jsonObjectBuilder.build();
    }


    private JsonArrayBuilder toJson(int[] value) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int item : value) {
            builder.add(item);
        }
        return builder;
    }

    private JsonArrayBuilder toJson(float[] value) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (float item : value) {
            builder.add(item);
        }
        return builder;
    }


    private JsonArrayBuilder toJson(double[] value) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (double item : value) {
            builder.add(item);
        }
        return builder;
    }

    private JsonArrayBuilder toJson(Collection value) {
        final JsonArrayBuilder builder = Json.createArrayBuilder();
        value.forEach(item -> {
            builder.add(toJson(item));
        });
        ;
        return builder;
    }

    private JsonObjectBuilder toJson(Map<Object, Object> value) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        value.forEach(
                (key, val) -> builder.add(key.toString(), toJson(val))
        );
        return builder;
    }


    private void processField(Object object, JsonObjectBuilder jsonObjectBuilder, Field field) throws IllegalAccessException {
        Object value = field.get(object);
        String name = field.getName();
        String typeName = field.getType().getName();

        if (typeName.equals("int") || object instanceof Integer) {
            jsonObjectBuilder.add(name, field.getInt(object));
        }

        if (typeName.equals("float") || object instanceof Float) {
            jsonObjectBuilder.add(name, field.getFloat(object));
        }

        if (typeName.equals("double") || object instanceof Double) {
            jsonObjectBuilder.add(name, field.getDouble(object));
        }

        if (field.getType().isArray()) {
            jsonObjectBuilder.add(name, processArrayField(object, field));
        } else {
            jsonObjectBuilder.add(name, toJson(value));
        }

    }


    private JsonArrayBuilder processArrayField(Object object, Field field) throws IllegalAccessException {
        Object value = field.get(object);

        Class<?> componentType = field.getType().getComponentType();
        if (componentType.isPrimitive()) {
            if (componentType.getName().equals("int")) {
                return toJson((int[]) value);
            }
            if (componentType.getName().equals("float")) {
                return toJson((float[]) value);
            }
            if (componentType.getName().equals("double")) {
                return toJson((double[]) value);
            }
        } else {
            if (componentType.isInstance(Integer.class)) {
                int[] convertedValue = Arrays.stream((Integer[]) value).mapToInt(Integer::intValue).toArray();
                return toJson(convertedValue);
            }

            if (componentType.isInstance(Float.class) || componentType.isInstance(Double.class)) {
                double[] convertedValue = Arrays.stream((Float[]) value).mapToDouble(Float::intValue).toArray();
                return toJson(convertedValue);
            }

            if (componentType == String.class) {
                List<String> list = Arrays.asList((String[]) value);
                return toJson(list);
            }
        }
        return Json.createArrayBuilder();
    }
}
