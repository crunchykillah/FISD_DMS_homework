package com.technokratos.manager;

import java.sql.SQLException;
import java.util.List;

public interface EntityManager {

    <T> T save(T entity) throws SQLException;
    <T> void remove(Class<T> entityType, Object key);
    <T> T find(Class<T> entityType, Object key);
    <T> List<T> findAll(Class<T> entityType);
}
