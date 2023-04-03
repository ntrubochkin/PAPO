package com.noname.books_exchange.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class OfferList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idOfferList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_book_literary")
    private BookLiterary bookLiterary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    private String isbn;
    private Date yearPublishing;
    private Timestamp createAt;
    private Timestamp updateAt;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    @OneToMany(mappedBy = "idList")
    private List<UserList> userLists;

    public OfferList() {
    }

    public OfferList(BookLiterary bookLiterary,
                     User user,
                     String isbn,
                     Date yearPublishing,
                     Status status) {
        this.bookLiterary = bookLiterary;
        this.user = user;
        this.isbn = isbn;
        this.yearPublishing = yearPublishing;
        this.createAt = new Timestamp(System.currentTimeMillis());;
        this.updateAt = getCreateAt();
        this.status = status;
    }

    public int getIdOfferList() {
        return idOfferList;
    }

    public void setIdOfferList(int idOfferList) {
        this.idOfferList = idOfferList;
    }

    public BookLiterary getBookLiterary() {
        return bookLiterary;
    }

    public void setBookLiterary(BookLiterary bookLiterary) {
        this.bookLiterary = bookLiterary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferList offerList = (OfferList) o;

        if (idOfferList != offerList.idOfferList) return false;
        if (!bookLiterary.equals(offerList.bookLiterary)) return false;
        if (!user.equals(offerList.user)) return false;
        if (!status.equals(offerList.status)) return false;
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
        result = 31 * result + bookLiterary.getIdBookLiterary();
        result = 31 * result + user.getIdUser();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (yearPublishing != null ? yearPublishing.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + status.getIdStatus();
        return result;
    }
}
