package com.example.labproject.models;

import java.util.List;

public class Client {

    private long idClient;
    private String type;
    private String model;
    private String ip;
    private List<Address> addressList;

    public Client(long idClient, String type, String model, String ip, List<Address> addressList) {
        this.idClient = idClient;
        this.type = type;
        this.model = model;
        this.ip = ip;
        this.addressList = addressList;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
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
