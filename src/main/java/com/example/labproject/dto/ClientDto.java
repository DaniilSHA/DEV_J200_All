package com.example.labproject.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class ClientDto {

    private int id;
    private String type;
    private String model;
    private String ip;
    private List<AddressDto> addressList;

    public ClientDto() {
    }

    public ClientDto(int id, String type, String model, String ip, List<AddressDto> addressList) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.ip = ip;
        this.addressList = addressList;
    }

    public List<AddressDto> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressDto> addressList) {
        this.addressList = addressList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
