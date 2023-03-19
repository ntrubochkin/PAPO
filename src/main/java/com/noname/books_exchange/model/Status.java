package com.noname.books_exchange.model;

import javax.persistence.*;

@Entity
@Table(name = " Status", schema = "public", catalog = "book_exchange")
public class Status {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdStatus", nullable = false)
    private int idStatus;
    @Basic
    @Column(name = "Name", nullable = true, length = 10)
    private String name;

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (idStatus != status.idStatus) return false;
        if (name != null ? !name.equals(status.name) : status.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatus;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
