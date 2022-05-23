package com.example.labproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADDRESSES")
@NamedQueries({
        @NamedQuery(name="addresses.findAll", query = "SELECT t FROM Address t")
})
public class Address {

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID")
    private int idAddress;
    @NotNull
    @Size(max=100)
    @Column(name = "CITY")
    private String city;
    @NotNull
    @Size(max=100)
    @Column(name = "STREET")
    private String street;
    @NotNull
    @Column(name = "NUM")
    private int num;
    @NotNull
    @Column(name = "SUBNUM")
    private int subnum;
    @NotNull
    @Column(name = "FLAT")
    private int flat;
    @NotNull
    @Size(max=200)
    @Column(name = "EXTRA")
    private String extra;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    public Address(int idAddress, String city, String street, int num, int subnum, int flat, String extra) {
        this.idAddress = idAddress;
        this.city = city;
        this.street = street;
        this.num = num;
        this.subnum = subnum;
        this.flat = flat;
        this.extra = extra;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
