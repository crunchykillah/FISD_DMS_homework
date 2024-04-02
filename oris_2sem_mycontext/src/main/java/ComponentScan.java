import annotation.Component;
import annotation.Inject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ComponentScan {
    private final Map<Class<?>, Object> components = new HashMap<>();

    public void scan() {
        try {
            URL resource = getClass().getResource("");
            if (resource != null) {
                String basePath = new File(resource.toURI()).getPath();
                scanPackage(basePath, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanPackage(String path, String packageName) {
        File directory = new File(path);
        //System.out.println(path);
        if (!directory.exists()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //System.out.println(file.getAbsolutePath());
                    scanPackage(file.getAbsolutePath(), file.getName());
                } else {
                    processClass(packageName, file);
                }
            }
        }
    }

    private void processClass(String packageName, File file) {
        try {
            String fileName = file.getName().replace("/","").replace(".class","");
            System.out.println("file           " + fileName);
            String className = packageName.isEmpty() ? fileName : packageName + "." + fileName;
            System.out.println("className      " + className);
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Component.class)) {
                addComponent(clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initializeComponents() {
        for (Object component : components.values()) {
            initializeDependencies(component);
        }
    }

    private void initializeDependencies(Object component) {
        Class<?> clazz = component.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Object dependency = components.get(field.getType());
                if (dependency != null) {
                    try {
                        field.setAccessible(true);
                        field.set(component, dependency);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addComponent(Class<?> clazz) {
        try {
            Object instance = createInstance(clazz);
            components.put(clazz, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object createInstance(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    public <T> T getComponent(Class<T> clazz) {
        return clazz.cast(components.get(clazz));
    }

}
