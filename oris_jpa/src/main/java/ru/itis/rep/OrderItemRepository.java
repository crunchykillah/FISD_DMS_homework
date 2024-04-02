package ru.itis.rep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itis.model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository {

    private final EntityManagerFactory emf;

    public OrderItem save(OrderItem orderItem) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (orderItem.getOrder() != null) {
            orderItem = entityManager.merge(orderItem);
        } else {
            entityManager.persist(orderItem);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return orderItem;
    }

}
