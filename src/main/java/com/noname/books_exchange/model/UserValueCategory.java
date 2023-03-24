package com.noname.books_exchange.model;

import jakarta.persistence.*;

@Entity
public class UserValueCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserValueCategory;
    private int idUserList;
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
