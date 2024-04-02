package com.technokratos.component;

import com.technokratos.manager.EntityManager;
import com.technokratos.model.Musician;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MusicianRepository {

    private final EntityManager entityManager;

    public Musician save(Musician musician) {
        try {
            return entityManager.save(musician);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        entityManager.remove(Musician.class, id);
    }

    public Musician find(Integer id) {
        return entityManager.find(Musician.class, id);
    }

    public List<Musician> findAll() {
        return entityManager.findAll(Musician.class);
    }
}
