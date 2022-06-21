package com.example.labproject.rest;

import com.example.labproject.jpa.DbManager;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/client")
@ApplicationPath("/web-res")
public class RestClientService extends Application {

    @EJB
    DbManager dbManager;

    @GET
    @Path("/{id}")
//    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public String getObject(@PathParam("id") Integer id) {
        return "" + id;
    }
}
