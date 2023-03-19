package com.noname.books_exchange.model;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class OfferList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdOfferList", nullable = false)
    private int idOfferList;
    @Basic
    @Column(name = "IdBookLiterary", nullable = false)
    private int idBookLiterary;
    @Basic
    @Column(name = "IdUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "ISBN", nullable = true, length = 13)
    private String isbn;
    @Basic
    @Column(name = "YearPublishing", nullable = false)
    private Date yearPublishing;
    @Basic
    @Column(name = "CreateAt", nullable = false)
    private Timestamp createAt;
    @Basic
    @Column(name = "UpdateAt", nullable = false)
    private Timestamp updateAt;
    @Basic
    @Column(name = "IdStatus", nullable = false)
    private int idStatus;

    public int getIdOfferList() {
        return idOfferList;
    }

    public void setIdOfferList(int idOfferList) {
        this.idOfferList = idOfferList;
    }

    public int getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(int idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(Date yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferList offerList = (OfferList) o;

        if (idOfferList != offerList.idOfferList) return false;
        if (idBookLiterary != offerList.idBookLiterary) return false;
        if (idUser != offerList.idUser) return false;
        if (idStatus != offerList.idStatus) return false;
        if (isbn != null ? !isbn.equals(offerList.isbn) : offerList.isbn != null) return false;
        if (yearPublishing != null ? !yearPublishing.equals(offerList.yearPublishing) : offerList.yearPublishing != null)
            return false;
        if (createAt != null ? !createAt.equals(offerList.createAt) : offerList.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(offerList.updateAt) : offerList.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOfferList;
        result = 31 * result + idBookLiterary;
        result = 31 * result + idUser;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (yearPublishing != null ? yearPublishing.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + idStatus;
        return result;
    }
}
