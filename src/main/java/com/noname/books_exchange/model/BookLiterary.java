package com.noname.books_exchange.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookLiterary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idBookLiterary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_author")
    private Author author;

    private String bookName;
    private String note;

    @OneToMany(mappedBy = "bookLiterary")
    private List<BookResponse> bookResponseList;

    public BookLiterary() {

    }

    public BookLiterary(Author author, String bookName) {
        this.author = author;
        this.bookName = bookName;
    }

    public int getIdBookLiterary() {
        return idBookLiterary;
    }

    public void setIdBookLiterary(int idBookLiterary) {
        this.idBookLiterary = idBookLiterary;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
        if (!author.equals(that.author)) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBookLiterary;
        result = 31 * result + author.getIdAuthor();
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
