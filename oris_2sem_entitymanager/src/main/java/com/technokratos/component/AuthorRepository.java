package com.technokratos.component;

import com.technokratos.manager.EntityManager;
import com.technokratos.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AuthorRepository {

    private final EntityManager entityManager;

    public Author save(Author author) {
        try {
            return entityManager.save(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        entityManager.remove(Author.class, id);
    }

    public Author find(Integer id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findAll() {
        return entityManager.findAll(Author.class);
    }
}
