package com.noname.books_exchange.model;

import javax.persistence.*;

@Entity
@Table
public class BookLiterary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdBookLiterary", nullable = false)
    private int idBookLiterary;
    @Basic
    @Column(name = "IdAuthor", nullable = false)
    private int idAuthor;
    @Basic
    @Column(name = "BookName", nullable = false, length = 50)
    private String bookName;
    @Basic
    @Column(name = "Note", nullable = true, length = 50)
    private String note;

    public int getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(int idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

        BookLiterary that = (BookLiterary) o;

        if (idBookLiterary != that.idBookLiterary) return false;
        if (idAuthor != that.idAuthor) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBookLiterary;
        result = 31 * result + idAuthor;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}