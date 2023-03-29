package com.noname.books_exchange.model;

import jakarta.persistence.*;

@Entity
public class UserExchangeList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserExchangeList;

    @ManyToOne
    @JoinColumn(name = "id_exchange_list")
    private ExchangeList exchangeList;

    @OneToOne
    @JoinColumn(name = "id_offer_list")
    private OfferList offerList;

    private String trackNumber;
    private boolean receiving;

    public int getIdUserExchangeList() {
        return idUserExchangeList;
    }

    public void setIdUserExchangeList(int idUserExchangeList) {
        this.idUserExchangeList = idUserExchangeList;
    }

    public ExchangeList getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(ExchangeList exchangeList) {
        this.exchangeList = exchangeList;
    }

    public OfferList getOfferList() {
        return offerList;
    }

    public void setOfferList(OfferList offerList) {
        this.offerList = offerList;
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
        if (!exchangeList.equals(that.exchangeList)) return false;
        if (!offerList.equals(that.offerList)) return false;
        if (receiving != that.receiving) return false;
        if (trackNumber != null ? !trackNumber.equals(that.trackNumber) : that.trackNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserExchangeList;
        result = 31 * result + exchangeList.getIdExchangeList();
        result = 31 * result + offerList.getIdOfferList();
        result = 31 * result + (trackNumber != null ? trackNumber.hashCode() : 0);
        result = 31 * result + (receiving ? 1 : 0);
        return result;
    }
}
