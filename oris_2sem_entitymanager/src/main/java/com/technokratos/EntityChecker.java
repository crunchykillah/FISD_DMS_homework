package com.technokratos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EntityChecker {

    private final DataSource dataSource;

    private final EntityScanner entityScanner;
    public boolean checkEntityStructure() {

        entityScanner.scan();

        Map<Class<?>,Object> entityClasses = entityScanner.getEntityMap();

        for (Map.Entry<Class<?>,Object> entity : entityClasses.entrySet()) {
            System.out.println(entity.getKey().getSimpleName());
            String tableName = getTableName(entity.getKey());
            System.out.println(tableName + " table name");

            if (tableName == null || !tableName.equalsIgnoreCase(entity.getKey().getSimpleName())) {
                return false;
            }
        }

        return true;
    }

    private String getTableName(Class<?> entityClass) {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, entityClass.getSimpleName().toLowerCase(), null);
            if (resultSet.next()) {
                return resultSet.getString("TABLE_NAME");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
