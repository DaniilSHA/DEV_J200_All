package com.example.labproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;

@Entity
@Table(name = "CLIENTS")
@NamedQueries({
        @NamedQuery(name="clients.findAll", query = "SELECT c FROM Client c"),
        @NamedQuery(name="clients.findById", query = "SELECT c FROM Client c WHERE c.idClient = :id")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID")
    private int idClient;
    @NotNull
    @Size(max=100)
    @Column(name = "TYPE")
    private String type;
    @NotNull
    @Size(max=100)
    @Column(name = "MODEL")
    private String model;
    @NotNull
    @Size(max=25)
    @Column(name = "IP")
    private String ip;
    @XmlTransient
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<Address> addressList;

    public Client(int idClient, String type, String model, String ip, List<Address> addressList) {
        this.idClient = idClient;
        this.type = type;
        this.model = model;
        this.ip = ip;
        this.addressList = addressList;
    }

    public Client(String type, String model, String ip, List<Address> addressList) {
        this.type = type;
        this.model = model;
        this.ip = ip;
        this.addressList = addressList;
    }

    public Client() {
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
