package com.noname.books_exchange.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class WishList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idWishList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    private Timestamp createAt;
    private Timestamp updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_address")
    private UserAddress userAddress;

    @OneToMany(mappedBy = "idList")
    private List<UserList> userLists;

    public int getIdWishList() {
        return idWishList;
    }

    public void setIdWishList(int idWishList) {
        this.idWishList = idWishList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishList wishList = (WishList) o;

        if (idWishList != wishList.idWishList) return false;
        if (!user.equals(wishList.user)) return false;
        if (!status.equals(wishList.status)) return false;
        if (!userAddress.equals(wishList.userAddress)) return false;
        if (createAt != null ? !createAt.equals(wishList.createAt) : wishList.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(wishList.updateAt) : wishList.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWishList;
        result = 31 * result + user.getIdUser();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + status.getIdStatus();
        result = 31 * result + userAddress.getIdUserAddress();
        return result;
    }
}
