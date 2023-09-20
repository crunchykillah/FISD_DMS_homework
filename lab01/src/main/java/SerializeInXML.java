import java.lang.reflect.Field;

public class SerializeInXML {
    public static String serialize(Object object) {
        return serializeObject(object);
    }
    public static String serializeWithOutField(Object object) {
        StringBuilder xml = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
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
            xml.append("  <").append(name).append(">");
            if (value instanceof String || isPrimitive(value.getClass())) {
                xml.append(value);
            } else {
                xml.append("\n").append(serializeWithOutField(value));}
            xml.append("</").append(name).append(">\n");
        }
        return xml.toString();
    }
    private static String serializeObject(Object object) {
        StringBuilder xml = new StringBuilder();
        String className = object.getClass().getSimpleName();
        xml.append("<").append(className).append(">\n");
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
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
            xml.append("  <").append(name).append(">");
            if (value instanceof String || isPrimitive(value.getClass())) {
                xml.append(value);
            } else {
                xml.append("\n").append(serializeWithOutField(value));
            }
            xml.append("</").append(name).append(">\n");
        }
        xml.append("</").append(className).append(">\n");
        return xml.toString();
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type == String.class || type == Integer.class ||
                type == Long.class || type == Double.class || type == Boolean.class;
    }
}
