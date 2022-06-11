package com.example.labproject.ejb;

import com.example.labproject.models.Client;
import com.example.labproject.models.ClientsListXmlDto;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DemoSAX extends DefaultHandler {

    private String param;
    private ClientsListXmlDto.ClientDto clientDto;
    private List<ClientsListXmlDto.ClientDto> parserClientsFinder = new ArrayList<>();
    private HttpServletResponse response;
    private HttpServletRequest request;


    public DemoSAX(String param, HttpServletResponse response, HttpServletRequest request) {
        this.param = param;
        this.response = response;
        this.request = request;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("client")) {
            String model = attributes.getValue("model");
            if (!model.equals(param)) return;
            String id = attributes.getValue("id");
            String type = attributes.getValue("type");
            String ip = attributes.getValue("ip");
            parserClientsFinder.add(new ClientsListXmlDto.ClientDto(Long.parseLong(id), type, model, ip));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            response.getWriter().println(parserClientsFinder.stream().map(ClientsListXmlDto.ClientDto::toString).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.endDocument();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }
}
