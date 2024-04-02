package com.technokratos.repository;

import com.technokratos.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManagerFactory emf;

    public Product save(Product product) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (product.getProduct_id() != null) {
            product = entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return product;
    }

}
