package com.example.labproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
@NamedQueries({
        @NamedQuery(name="addresses.findAll", query = "SELECT t FROM Address t")
})
public class Address {

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "id")
    private long idAddress;
    @NotNull
    @Size(max=100)
    private String city;
    @NotNull
    @Size(max=100)
    private String street;
    @NotNull
    private int num;
    @NotNull
    private int subnum;
    @NotNull
    private int flat;
    @NotNull
    @Size(max=200)
    private String extra;

    public Address(int idAddress, String city, String street, int num, int subnum, int flat, String extra) {
        this.idAddress = idAddress;
        this.city = city;
        this.street = street;
        this.num = num;
        this.subnum = subnum;
        this.flat = flat;
        this.extra = extra;
    }

    public Address() {
    }

    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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
