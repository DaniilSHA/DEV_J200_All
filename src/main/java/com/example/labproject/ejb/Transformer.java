package com.example.labproject.ejb;

import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.ClientsListXmlDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


@Stateless
public class Transformer {

    @EJB
    private DbManager dbManager;

    public void transform () {

        ClientsListXmlDto clientsList = new ClientsListXmlDto(dbManager.loadAllClients());

        try {
            JAXBContext context = JAXBContext.newInstance(ClientsListXmlDto.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File clients = new File("./clients.xml");
            mar.marshal(clientsList, clients);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
