package com.noname.books_exchange.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class ExchangeList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idExchangeList;

    @OneToOne
    @JoinColumn(name = "id_offer_list1")
    private OfferList offerList1;

    @OneToOne
    @JoinColumn(name = "id_wish_list1")
    private WishList wishList1;

    @OneToOne
    @JoinColumn(name = "id_offer_list2")
    private OfferList offerList2;

    @OneToOne
    @JoinColumn(name = "id_wish_list2")
    private WishList wishList2;

    private Timestamp createAt;
    private boolean isBoth;

    @OneToMany(mappedBy = "exchangeList")
    private List<UserExchangeList> userExchangeLists;

    public int getIdExchangeList() {
        return idExchangeList;
    }

    public void setIdExchangeList(int idExchangeList) {
        this.idExchangeList = idExchangeList;
    }

    public OfferList getOfferList1() {
        return offerList1;
    }

    public void setOfferList1(OfferList offerList1) {
        this.offerList1 = offerList1;
    }

    public WishList getWishList1() {
        return wishList1;
    }

    public void setWishList1(WishList wishList1) {
        this.wishList1 = wishList1;
    }

    public OfferList getOfferList2() {
        return offerList2;
    }

    public void setOfferList2(OfferList offerList2) {
        this.offerList2 = offerList2;
    }

    public WishList getWishList2() {
        return wishList2;
    }

    public void setWishList2(WishList wishList2) {
        this.wishList2 = wishList2;
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
        if (!offerList1.equals(that.offerList1)) return false;
        if (!wishList1.equals(that.wishList1)) return false;
        if (!offerList2.equals(that.offerList2)) return false;
        if (!wishList2.equals(that.wishList2)) return false;
        if (isBoth != that.isBoth) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExchangeList;
        result = 31 * result + offerList1.getIdOfferList();
        result = 31 * result + wishList1.getIdWishList();
        result = 31 * result + offerList2.getIdOfferList();
        result = 31 * result + wishList2.getIdWishList();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (isBoth ? 1 : 0);
        return result;
    }
}
