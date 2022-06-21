package com.example.labproject.rest;

import com.example.labproject.dto.AddressDto;
import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.Address;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/address")
@ApplicationPath("/web-res")
public class RestAddressService {

    @EJB
    DbManager dbManager;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public AddressDto getAddress(@PathParam("id") Integer id) {
        Address addressById = dbManager.findAddressById(id);
        return new AddressDto(
                addressById.getIdAddress(),
                addressById.getCity(),
                addressById.getStreet(),
                addressById.getNum(),
                addressById.getSubnum(),
                addressById.getFlat(),
                addressById.getExtra()
        );
    }

    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_XML })
    public List<AddressDto> getAddresses() {
        List<Address> allAddresses = dbManager.loadAllAddress();
        return allAddresses.stream().map(addr -> {
                            return new AddressDto(
                                    addr.getIdAddress(),
                                    addr.getCity(),
                                    addr.getStreet(),
                                    addr.getNum(),
                                    addr.getSubnum(),
                                    addr.getFlat(),
                                    addr.getExtra()
                            );
                        }).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public void deleteAddress(@PathParam("id") Integer id) {
        dbManager.deleteAddressById(id);
    }
}
