import java.lang.reflect.Field;

public class SerializeInJson {
    public static String serialize(Object object) {
        return serializeObject(object);
    }
    public static String serializeWithOutField(Object object) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                continue;
            }
            json.append("  \"").append(name).append("\": ");

            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else if (isPrimitive(value.getClass())) {
                json.append(value);
            } else {
                json.append(serializeWithOutField(value));
            }
            if (i < fields.length - 1) {
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }

    private static String serializeObject(Object object) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Получаем имя класса и преобразуем его в нижний регистр
        String className = object.getClass().getSimpleName().toLowerCase();
        json.append("\"").append(className).append("\": {");

        // Получаем все поля объекта с их значениями
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;

            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // Игнорируем поля со значением null
            if (value == null) {
                continue;
            }

            // Добавляем поле и его значение в JSON
            json.append("  \"").append(name).append("\": ");

            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else if (isPrimitive(value.getClass())) {
                json.append(value);
            } else {
                json.append(serializeWithOutField(value));
            }
            if (i < fields.length - 1) {
                json.append(",");
            }

        }
        json.append("}\n");
        return json.toString();
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type == String.class || type == Integer.class ||
                type == Long.class || type == Double.class || type == Boolean.class;
    }
}
