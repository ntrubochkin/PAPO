package com.noname.books_exchange.model;

import javax.persistence.*;

@Entity
public class UserExchangeList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdUserExchangeList", nullable = false)
    private int idUserExchangeList;
    @Basic
    @Column(name = "IdExchangeList", nullable = false)
    private int idExchangeList;
    @Basic
    @Column(name = "IdOfferList", nullable = false)
    private int idOfferList;
    @Basic
    @Column(name = "TrackNumber", nullable = true, length = 14)
    private String trackNumber;
    @Basic
    @Column(name = "Receiving", nullable = false)
    private boolean receiving;

    public int getIdUserExchangeList() {
        return idUserExchangeList;
    }

    public void setIdUserExchangeList(int idUserExchangeList) {
        this.idUserExchangeList = idUserExchangeList;
    }

    public int getIdExchangeList() {
        return idExchangeList;
    }

    public void setIdExchangeList(int idExchangeList) {
        this.idExchangeList = idExchangeList;
    }

    public int getIdOfferList() {
        return idOfferList;
    }

    public void setIdOfferList(int idOfferList) {
        this.idOfferList = idOfferList;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public boolean isReceiving() {
        return receiving;
    }

    public void setReceiving(boolean receiving) {
        this.receiving = receiving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExchangeList that = (UserExchangeList) o;

        if (idUserExchangeList != that.idUserExchangeList) return false;
        if (idExchangeList != that.idExchangeList) return false;
        if (idOfferList != that.idOfferList) return false;
        if (receiving != that.receiving) return false;
        if (trackNumber != null ? !trackNumber.equals(that.trackNumber) : that.trackNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserExchangeList;
        result = 31 * result + idExchangeList;
        result = 31 * result + idOfferList;
        result = 31 * result + (trackNumber != null ? trackNumber.hashCode() : 0);
        result = 31 * result + (receiving ? 1 : 0);
        return result;
    }
}
