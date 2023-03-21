package com.noname.books_exchange.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserAddress")
public class UserAddress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdUserAddress", nullable = false)
    private int idUserAddress;
    @Basic
    @Column(name = "IdUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "AddrIndex", nullable = false, length = 6)
    private String addrIndex;
    @Basic
    @Column(name = "AddrCity", nullable = false, length = 15)
    private String addrCity;
    @Basic
    @Column(name = "AddrStreet", nullable = false, length = 25)
    private String addrStreet;
    @Basic
    @Column(name = "AddrHouse", nullable = false, length = 5)
    private String addrHouse;
    @Basic
    @Column(name = "AddrStructure", nullable = true, length = 10)
    private String addrStructure;
    @Basic
    @Column(name = "AddrApart", nullable = true, length = 3)
    private String addrApart;
    @Basic
    @Column(name = "IsDefault ", nullable = false)
    private boolean isDefault;

    public UserAddress(int idUser, String addrIndex, String addrCity, String addrStreet, String addrHouse, String addrStructure, String addrApart) {
        this.idUser = idUser;
        this.addrIndex = addrIndex;
        this.addrCity = addrCity;
        this.addrStreet = addrStreet;
        this.addrHouse = addrHouse;
        this.addrStructure = addrStructure;
        this.addrApart = addrApart;
    }

    public int getIdUserAddress() {
        return idUserAddress;
    }

    public void setIdUserAddress(int idUserAddress) {
        this.idUserAddress = idUserAddress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAddrIndex() {
        return addrIndex;
    }

    public void setAddrIndex(String addrIndex) {
        this.addrIndex = addrIndex;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrHouse() {
        return addrHouse;
    }

    public void setAddrHouse(String addrHouse) {
        this.addrHouse = addrHouse;
    }

    public String getAddrStructure() {
        return addrStructure;
    }

    public void setAddrStructure(String addrStructure) {
        this.addrStructure = addrStructure;
    }

    public String getAddrApart() {
        return addrApart;
    }

    public void setAddrApart(String addrApart) {
        this.addrApart = addrApart;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAddress that = (UserAddress) o;

        if (idUserAddress != that.idUserAddress) return false;
        if (idUser != that.idUser) return false;
        if (isDefault != that.isDefault) return false;
        if (addrIndex != null ? !addrIndex.equals(that.addrIndex) : that.addrIndex != null) return false;
        if (addrCity != null ? !addrCity.equals(that.addrCity) : that.addrCity != null) return false;
        if (addrStreet != null ? !addrStreet.equals(that.addrStreet) : that.addrStreet != null) return false;
        if (addrHouse != null ? !addrHouse.equals(that.addrHouse) : that.addrHouse != null) return false;
        if (addrStructure != null ? !addrStructure.equals(that.addrStructure) : that.addrStructure != null)
            return false;
        if (addrApart != null ? !addrApart.equals(that.addrApart) : that.addrApart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserAddress;
        result = 31 * result + idUser;
        result = 31 * result + (addrIndex != null ? addrIndex.hashCode() : 0);
        result = 31 * result + (addrCity != null ? addrCity.hashCode() : 0);
        result = 31 * result + (addrStreet != null ? addrStreet.hashCode() : 0);
        result = 31 * result + (addrHouse != null ? addrHouse.hashCode() : 0);
        result = 31 * result + (addrStructure != null ? addrStructure.hashCode() : 0);
        result = 31 * result + (addrApart != null ? addrApart.hashCode() : 0);
        result = 31 * result + (isDefault ? 1 : 0);
        return result;
    }
}
