package com.example.labproject.models;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "clients")
//@jakarta.xml.bind.annotation.XmlRootElement(name = "clients")
public class ClientsListXmlDto {

    @XmlElementWrapper(name="client-list", nillable = true)
    @XmlElement(name = "client")
    private List<ClientDto> clients;

    public ClientsListXmlDto(List<Client> clients) {
        this.clients = clients.stream().map(ClientsListXmlDto::toClientDto).collect(Collectors.toList());
    }

    public ClientsListXmlDto() {
    }

//    @XmlRootElement(name = "client")
    public static class ClientDto {


        @XmlAttribute
        private long id;
        @XmlAttribute
        private String type;
        @XmlAttribute
        private String model;
        @XmlAttribute
        private String ip;
//        @XmlElementWrapper(name="addresses", nillable = true)
//        private List<AddressDto> addresses;

        public ClientDto(long id, String type, String model, String ip, List<Address> addresses) {
            this.id = id;
            this.type = type;
            this.model = model;
            this.ip = ip;
//            this.addresses = addresses.stream().map(addr -> new AddressDto(
//                    addr.getIdAddress(),
//                    addr.getCity(),
//                    addr.getStreet(),
//                    addr.getNum(),
//                    addr.getSubnum(),
//                    addr.getFlat(),
//                    addr.getExtra()
//            )).collect(Collectors.toList());
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

//    @XmlRootElement(name = "address")
//    public static class AddressDto {
//        @XmlAttribute
//        private int idAddress;
//        @XmlAttribute
//        private String city;
//        @XmlAttribute
//        private String street;
//        @XmlAttribute
//        private int num;
//        @XmlAttribute
//        private int subnum;
//        @XmlAttribute
//        private int flat;
//        @XmlAttribute
//        private String extra;
//
//        public AddressDto(int idAddress, String city, String street, int num, int subnum, int flat, String extra) {
//            this.idAddress = idAddress;
//            this.city = city;
//            this.street = street;
//            this.num = num;
//            this.subnum = subnum;
//            this.flat = flat;
//            this.extra = extra;
//        }
//
//        public int getIdAddress() {
//            return idAddress;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public String getStreet() {
//            return street;
//        }
//
//        public int getNum() {
//            return num;
//        }
//
//        public int getSubnum() {
//            return subnum;
//        }
//
//        public int getFlat() {
//            return flat;
//        }
//
//        public String getExtra() {
//            return extra;
//        }
//
//        @Override
//        public String toString() {
//            return "AddressDto{" +
//                    "idAddress=" + idAddress +
//                    ", city='" + city + '\'' +
//                    ", street='" + street + '\'' +
//                    ", num=" + num +
//                    ", subnum=" + subnum +
//                    ", flat=" + flat +
//                    ", extra='" + extra + '\'' +
//                    '}';
//        }
//    }


    public static ClientDto toClientDto(Client client) {
        return new ClientDto(client.getIdClient(), client.getType(), client.getModel(), client.getIp(), client.getAddressList());
    }
}
