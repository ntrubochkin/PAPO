package com.noname.books_exchange.model;

import javax.persistence.*;

@Entity
public class UserValueCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdUserValueCategory", nullable = false)
    private int idUserValueCategory;
    @Basic
    @Column(name = "IdUserList", nullable = false)
    private int idUserList;
    @Basic
    @Column(name = "IdCategory", nullable = false)
    private int idCategory;

    public int getIdUserValueCategory() {
        return idUserValueCategory;
    }

    public void setIdUserValueCategory(int idUserValueCategory) {
        this.idUserValueCategory = idUserValueCategory;
    }

    public int getIdUserList() {
        return idUserList;
    }

    public void setIdUserList(int idUserList) {
        this.idUserList = idUserList;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserValueCategory that = (UserValueCategory) o;

        if (idUserValueCategory != that.idUserValueCategory) return false;
        if (idUserList != that.idUserList) return false;
        if (idCategory != that.idCategory) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserValueCategory;
        result = 31 * result + idUserList;
        result = 31 * result + idCategory;
        return result;
    }
}
