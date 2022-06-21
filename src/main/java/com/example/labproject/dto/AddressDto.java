package com.example.labproject.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressDto {

    private int id;
    private String city;
    private String street;
    private int num;
    private int subnum;
    private int flat;
    private String extra;

    public AddressDto() {
    }

    public AddressDto(int id, String city, String street, int num, int subnum, int flat, String extra) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.num = num;
        this.subnum = subnum;
        this.flat = flat;
        this.extra = extra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSubnum() {
        return subnum;
    }

    public void setSubnum(int subnum) {
        this.subnum = subnum;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
