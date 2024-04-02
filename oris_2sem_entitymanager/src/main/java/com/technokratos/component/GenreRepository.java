package com.technokratos.component;

import com.technokratos.manager.EntityManager;
import com.technokratos.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class GenreRepository {

    private final EntityManager entityManager;

    public Genre save(Genre genre) {
        try {
            return entityManager.save(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        entityManager.remove(Genre.class, id);
    }

    public Genre find(Integer id) {
        return entityManager.find(Genre.class, id);
    }

    public List<Genre> findAll() {
        return entityManager.findAll(Genre.class);
    }
}
