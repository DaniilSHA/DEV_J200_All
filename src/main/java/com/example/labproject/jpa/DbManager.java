package com.example.labproject.jpa;

import com.example.labproject.exceptions.UserNotFoundException;
import com.example.labproject.models.Client;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Singleton
public class DbManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Client> loadAllClients() {
        return entityManager.createNamedQuery("clients.findAll").getResultList();
    }

    public void deleteById(int id) {
        Client client = entityManager.createNamedQuery("clients.findById", Client.class).setParameter("id", id).getSingleResult();
        if (client == null) throw new UserNotFoundException(id);
        entityManager.remove(client);
    }
}
