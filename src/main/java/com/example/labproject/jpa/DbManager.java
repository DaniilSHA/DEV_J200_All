package com.example.labproject.jpa;

import com.example.labproject.exceptions.AddressNotFoundException;
import com.example.labproject.exceptions.ClientNotFoundException;
import com.example.labproject.models.Address;
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

    public void deleteClientById(int id) throws ClientNotFoundException {
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

    public List<Address> loadAllAddress() {
        return entityManager.createNamedQuery("addresses.findAll").getResultList();
    }

    public Address findAddressById(int id) throws AddressNotFoundException {
        return entityManager.createNamedQuery("addresses.findById", Address.class).setParameter("id", id).getSingleResult();
    }

    public void deleteAddressById(int id) throws AddressNotFoundException {
        Address address = entityManager.createNamedQuery("addresses.findById", Address.class).setParameter("id", id).getSingleResult();
        if (address == null) throw new AddressNotFoundException(id);
        entityManager.remove(address);
    }
}
