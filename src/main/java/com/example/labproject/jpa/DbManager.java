package com.example.labproject.jpa;

import com.example.labproject.exceptions.ClientNotFoundException;
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

    public void deleteById(int id) throws ClientNotFoundException {
        Client client = entityManager.createNamedQuery("clients.findById", Client.class).setParameter("id", id).getSingleResult();
        if (client == null) throw new ClientNotFoundException(id);
        entityManager.remove(client);
    }

    public void saveNewClient (Client client) {
        entityManager.persist(client);
    }

    public Client findClientById(int id) throws ClientNotFoundException {
        return entityManager.createNamedQuery("clients.findById", Client.class).setParameter("id", id).getSingleResult();
    }
}
