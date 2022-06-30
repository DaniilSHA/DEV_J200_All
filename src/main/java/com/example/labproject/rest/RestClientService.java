package com.example.labproject.rest;

import com.example.labproject.dto.AddressDto;
import com.example.labproject.dto.ClientDto;
import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.Client;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/client")
@ApplicationPath("/web-res")
public class RestClientService extends Application {

    @EJB
    DbManager dbManager;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public ClientDto getClient(@PathParam("id") Integer id) {
        Client clientById = dbManager.findClientById(id);
        return new ClientDto(
                (int) clientById.getIdClient(),
                clientById.getType(),
                clientById.getModel(),
                clientById.getIp(),
                clientById.getAddressList().stream().map(addr -> {
                    return new AddressDto(
                            addr.getIdAddress(),
                            addr.getCity(),
                            addr.getStreet(),
                            addr.getNum(),
                            addr.getSubnum(),
                            addr.getFlat(),
                            addr.getExtra()
                    );
                }).collect(Collectors.toList())
        );
    }


    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_XML })
    public List<ClientDto> getClients() {
        List<Client> allClients = dbManager.loadAllClients();
        return allClients.stream().map(client ->
            new ClientDto(
                    (int) client.getIdClient(),
                    client.getType(),
                    client.getModel(),
                    client.getIp(),
                    client.getAddressList().stream().map(addr -> {
                        return new AddressDto(
                                addr.getIdAddress(),
                                addr.getCity(),
                                addr.getStreet(),
                                addr.getNum(),
                                addr.getSubnum(),
                                addr.getFlat(),
                                addr.getExtra()
                        );
                    }).collect(Collectors.toList()))).collect(Collectors.toList());
    }



    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public void deleteClient(@PathParam("id") Integer id) {
        dbManager.deleteClientById(id);
    }
}
