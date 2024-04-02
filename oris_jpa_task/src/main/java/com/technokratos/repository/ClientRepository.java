package com.technokratos.repository;

import com.technokratos.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class ClientRepository {

    private final EntityManagerFactory emf;

    public Client save(Client client) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (client.getId() != null) {
            entityManager.merge(client);
        } else {
            entityManager.persist(client);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return client;
    }

}
