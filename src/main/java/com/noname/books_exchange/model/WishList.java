package com.noname.books_exchange.model;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class WishList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdWishList", nullable = false)
    private int idWishList;
    @Basic
    @Column(name = "IdUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "CreateAt", nullable = false)
    private Timestamp createAt;
    @Basic
    @Column(name = "UpdateAt", nullable = false)
    private Timestamp updateAt;
    @Basic
    @Column(name = "IdStatus", nullable = false)
    private int idStatus;
    @Basic
    @Column(name = "IdUserAddress", nullable = false)
    private int idUserAddress;

    public int getIdWishList() {
        return idWishList;
    }

    public void setIdWishList(int idWishList) {
        this.idWishList = idWishList;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getIdUserAddress() {
        return idUserAddress;
    }

    public void setIdUserAddress(int idUserAddress) {
        this.idUserAddress = idUserAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishList wishList = (WishList) o;

        if (idWishList != wishList.idWishList) return false;
        if (idUser != wishList.idUser) return false;
        if (idStatus != wishList.idStatus) return false;
        if (idUserAddress != wishList.idUserAddress) return false;
        if (createAt != null ? !createAt.equals(wishList.createAt) : wishList.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(wishList.updateAt) : wishList.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWishList;
        result = 31 * result + idUser;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + idStatus;
        result = 31 * result + idUserAddress;
        return result;
    }
}
