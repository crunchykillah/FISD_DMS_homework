package com.technokratos.component;

import com.technokratos.model.Country;
import lombok.RequiredArgsConstructor;
import com.technokratos.manager.EntityManager;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CountryRepository {

    private final EntityManager entityManager;

    public Country save(Country country) {
        try {
            return entityManager.save(country);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        entityManager.remove(Country.class, id);
    }

    public Country find(Integer id) {
        return entityManager.find(Country.class, id);
    }

    public List<Country> findAll() {
        return entityManager.findAll(Country.class);
    }
}
