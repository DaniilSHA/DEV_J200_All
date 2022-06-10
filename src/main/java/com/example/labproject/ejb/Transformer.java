package com.example.labproject.ejb;

import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.ClientsListXmlDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


@Stateless
public class Transformer {

    @EJB
    private DbManager dbManager;

    public File transform () {

        ClientsListXmlDto clientsList = new ClientsListXmlDto(dbManager.loadAllClients());
        File clients = new File("./clients.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(ClientsListXmlDto.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(clientsList, clients);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
