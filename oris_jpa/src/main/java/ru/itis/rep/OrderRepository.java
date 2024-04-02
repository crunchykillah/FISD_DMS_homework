package ru.itis.rep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itis.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManagerFactory emf;

    public Order save(Order order) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (order.getId() != null) {
            order = entityManager.merge(order);
        } else {
            entityManager.persist(order);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return order;
    }

}
