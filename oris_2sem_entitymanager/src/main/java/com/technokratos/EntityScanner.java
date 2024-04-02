package com.technokratos;

import com.technokratos.annotation.Entity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Getter
@Component
public class EntityScanner {

    private final Map<Class<?>, Object> entityMap = new HashMap<>();

    public void scan() {
        try {
            URL resource = getClass().getResource("");
            if (resource != null) {
                String basePath = new File(resource.toURI()).getPath();
                scanPackage(basePath, "com.technokratos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanPackage(String path, String packageName) {
        File directory = new File(path);
        System.out.println(path);
        if (!directory.exists()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //System.out.println(file.getAbsolutePath());
                    scanPackage(file.getAbsolutePath(), packageName + "." + file.getName());
                } else {
                    processClass(packageName, file);
                }
            }
        }
    }

    private void processClass(String packageName, File file) {
        try {
            String fileName = file.getName().replace("/","");
            if (fileName.endsWith(".class")) {
                fileName = fileName.replace(".class","");
                System.out.println("file           " + fileName);
                String className = packageName.isEmpty() ? fileName : packageName + "." + fileName;
                System.out.println("className      " + className);
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Entity.class)) {
                    addEntity(clazz);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEntity(Class<?> clazz) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            entityMap.put(clazz, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getEntity(Class<T> clazz) {
        return clazz.cast(entityMap.get(clazz));
    }
}
