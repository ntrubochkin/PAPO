package com.noname.books_exchange.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookResponse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdBookResponse", nullable = false)
    private int idBookResponse;
    @Basic
    @Column(name = "IdBookLiterary", nullable = false)
    private int idBookLiterary;
    @Basic
    @Column(name = "IdUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "CreateAt", nullable = false)
    private Timestamp createAt;
    @Basic
    @Column(name = "Response", nullable = false, length = 500)
    private String response;
    @Basic
    @Column(name = "Note", nullable = true, length = 50)
    private String note;

    public int getIdBookResponse() {
        return idBookResponse;
    }

    public void setIdBookResponse(int idBookResponse) {
        this.idBookResponse = idBookResponse;
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
        if (idBookLiterary != that.idBookLiterary) return false;
        if (idUser != that.idUser) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (response != null ? !response.equals(that.response) : that.response != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBookResponse;
        result = 31 * result + idBookLiterary;
        result = 31 * result + idUser;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
