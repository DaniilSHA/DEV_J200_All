package com.example.labproject.ejb;

import com.example.labproject.models.ClientsListXmlDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoDOM {

    private File source;
    private String param;
    private HttpServletResponse response;
    private List<ClientsListXmlDto.ClientDto> parserClientsFinder = new ArrayList<>();

    public DemoDOM(File source, String param, HttpServletResponse response) {
        this.source = source;
        this.param = param;
        this.response = response;
    }

    public void find() {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);

            Element root = document.getDocumentElement();
            NodeList clientList = root.getChildNodes();
            NodeList clients = clientList.item(1).getChildNodes();
            for (int i = 0; i < clients.getLength(); i++) {

                Node client = clients.item(i).getNodeName().equals("client") ? clients.item(i) : null;

                Element cleintEl = null;
                if (client != null)
                    cleintEl = (Element) client;

                if (cleintEl != null && cleintEl.getAttribute("model").equals(param)) {

                    parserClientsFinder.add(new ClientsListXmlDto.ClientDto(
                            Long.parseLong(cleintEl.getAttribute("id")),
                            cleintEl.getAttribute("type"),
                            cleintEl.getAttribute("model"),
                            cleintEl.getAttribute("ip")
                    ));
                }
            }

            response.getWriter().println(parserClientsFinder.stream().map(ClientsListXmlDto.ClientDto::toString).collect(Collectors.joining("\n")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
