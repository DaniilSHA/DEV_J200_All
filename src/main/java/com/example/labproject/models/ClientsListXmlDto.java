package com.example.labproject.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "clients")
public class ClientsListXmlDto {

    @XmlElementWrapper(name="client-list", nillable = true)
    @XmlElement(name = "client")
    private List<ClientDto> clients;

    public ClientsListXmlDto(List<Client> clients) {
        this.clients = clients.stream().map(this::toClientDto).collect(Collectors.toList());
    }

    public ClientsListXmlDto() {
    }

    public static class ClientDto {


        @XmlAttribute
        private long id;
        @XmlAttribute
        private String type;
        @XmlAttribute
        private String model;
        @XmlAttribute
        private String ip;

        public ClientDto(long id, String type, String model, String ip) {
            this.id = id;
            this.type = type;
            this.model = model;
            this.ip = ip;
        }

        public long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getModel() {
            return model;
        }

        public String getIp() {
            return ip;
        }

        @Override
        public String toString() {
            return "ClientDto{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", model='" + model + '\'' +
                    ", ip='" + ip + '\'' +
                    '}';
        }
    }

    private ClientDto toClientDto (Client client) {
        return new ClientDto(client.getIdClient(), client.getType(), client.getModel(), client.getIp());
    }
}
