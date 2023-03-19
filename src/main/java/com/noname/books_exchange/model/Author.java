package com.noname.books_exchange.model;

import javax.persistence.*;

@Entity
@Table
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdAuthor", nullable = false)
    private int idAuthor;
    @Basic
    @Column(name = "LastName", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "FirstName", nullable = true, length = 50)
    private String firstName;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (idAuthor != author.idAuthor) return false;
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthor;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
