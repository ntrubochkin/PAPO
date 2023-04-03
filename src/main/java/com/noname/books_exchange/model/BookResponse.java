package com.noname.books_exchange.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class BookResponse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idBookResponse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_book_literary")
    private BookLiterary bookLiterary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    private Timestamp createAt;
    private String response;
    private String note;

    public int getIdBookResponse() {
        return idBookResponse;
    }

    public void setIdBookResponse(int idBookResponse) {
        this.idBookResponse = idBookResponse;
    }

    public BookLiterary getBookLiterary() {
        return bookLiterary;
    }

    public void setBookLiterary(BookLiterary bookLiterary) {
        this.bookLiterary = bookLiterary;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookResponse that = (BookResponse) o;

        if (idBookResponse != that.idBookResponse) return false;
        if (!bookLiterary.equals(bookLiterary)) return false;
        if (!user.equals(that.user)) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (response != null ? !response.equals(that.response) : that.response != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBookResponse;
        result = 31 * result + bookLiterary.getIdBookLiterary();
        result = 31 * result + user.getIdUser();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
