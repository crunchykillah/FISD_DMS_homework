package com.technokratos;

import com.technokratos.annotation.Id;
import com.technokratos.annotation.ManyToOne;
import com.technokratos.manager.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class EntityManagerImpl implements EntityManager {

    private final DataSource dataSource;

    @Override
    public <T> T save(T entity) {
        String tableName = entity.getClass().getSimpleName().toLowerCase();
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder(" VALUES (");
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value != null) {
                    sql.append(fieldName).append(", ");
                    if (field.isAnnotationPresent(ManyToOne.class)) {
                        Field idField = value.getClass().getDeclaredField("id");
                        idField.setAccessible(true);
                        Object idValue = idField.get(value);
                        values.append("'").append(idValue).append("', ");
                    } else {
                        values.append("'").append(value).append("', ");
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        sql.deleteCharAt(sql.lastIndexOf(",")).append(")");
        values.deleteCharAt(values.lastIndexOf(",")).append(")");
        String insertQuery = sql.append(values).toString();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


    @Override
    public <T> void remove(Class<T> entityType, Object key) {
        String tableName = entityType.getSimpleName().toLowerCase();
        Field[] fields = entityType.getDeclaredFields();
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                String fieldName = field.getName();
                sql.append(fieldName).append(" = '").append(key).append("'");
                break;
            }
        }
        System.out.println(sql.toString());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
             statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T find(Class<T> entityType, Object key) {
        String tableName = entityType.getSimpleName().toLowerCase();
        Field[] fields = entityType.getDeclaredFields();
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                String fieldName = field.getName();
                sql.append(fieldName).append(" = '").append(key).append("'");
                break;
            }
        }
        System.out.println(sql.toString());
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet, entityType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityType) {
        List<T> entities = new ArrayList<>();
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + tableName;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet, entityType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private <T> T mapResultSetToEntity(ResultSet resultSet, Class<T> entityType) throws SQLException {
        T entity;
        try {
            entity = entityType.newInstance();
            Field[] fields = entityType.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName().toLowerCase();
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(entity, Class.forName("com.technokratos.model." + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).getDeclaredConstructor().newInstance());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    field.setAccessible(true);
                    field.set(entity, resultSet.getObject(fieldName));
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SQLException("Failed to map ResultSet to entity", e);
        }
        return entity;
    }
}
