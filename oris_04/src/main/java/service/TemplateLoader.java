package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TemplateLoader {
    public static String loadTemplateFromResource(String resourcePath) throws IOException {
        try (InputStream inputStream = TemplateLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } else {
                throw new IOException("Шаблон не найден:  " + resourcePath);
            }
        }
    }
}
