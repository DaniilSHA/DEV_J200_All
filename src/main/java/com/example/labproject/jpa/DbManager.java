package com.example.labproject.jpa;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Singleton
public class DbManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List loadAllClients() {

        return entityManager.createNamedQuery("clients.findAll").getResultList();
    }
}
