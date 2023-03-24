package com.noname.books_exchange.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class ExchangeList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idExchangeList;
    private int idOfferList1;
    private int idWishList1;
    private int idOfferList2;
    private int idWishList2;
    private Timestamp createAt;
    private boolean isBoth;

    public int getIdExchangeList() {
        return idExchangeList;
    }

    public void setIdExchangeList(int idExchangeList) {
        this.idExchangeList = idExchangeList;
    }

    public int getIdOfferList1() {
        return idOfferList1;
    }

    public void setIdOfferList1(int idOfferList1) {
        this.idOfferList1 = idOfferList1;
    }

    public int getIdWishList1() {
        return idWishList1;
    }

    public void setIdWishList1(int idWishList1) {
        this.idWishList1 = idWishList1;
    }

    public int getIdOfferList2() {
        return idOfferList2;
    }

    public void setIdOfferList2(int idOfferList2) {
        this.idOfferList2 = idOfferList2;
    }

    public int getIdWishList2() {
        return idWishList2;
    }

    public void setIdWishList2(int idWishList2) {
        this.idWishList2 = idWishList2;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public boolean isBoth() {
        return isBoth;
    }

    public void setBoth(boolean both) {
        isBoth = both;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeList that = (ExchangeList) o;

        if (idExchangeList != that.idExchangeList) return false;
        if (idOfferList1 != that.idOfferList1) return false;
        if (idWishList1 != that.idWishList1) return false;
        if (idOfferList2 != that.idOfferList2) return false;
        if (idWishList2 != that.idWishList2) return false;
        if (isBoth != that.isBoth) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExchangeList;
        result = 31 * result + idOfferList1;
        result = 31 * result + idWishList1;
        result = 31 * result + idOfferList2;
        result = 31 * result + idWishList2;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (isBoth ? 1 : 0);
        return result;
    }
}
