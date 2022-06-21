package com.example.labproject.rest;

import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.Client;
import com.example.labproject.models.ClientsListXmlDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/client")
public class ClientRestService {

    @EJB
    private DbManager dbManager;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public ClientsListXmlDto.ClientDto getPersonById(@PathParam("id") Integer id) {
        return ClientsListXmlDto.toClientDto(dbManager.findClientById(id));
    }
}