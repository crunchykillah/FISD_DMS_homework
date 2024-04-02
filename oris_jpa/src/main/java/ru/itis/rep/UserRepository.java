package ru.itis.rep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itis.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManagerFactory emf;

    public UserEntity save(UserEntity user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return user;
    }

}
