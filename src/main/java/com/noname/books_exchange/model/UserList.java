package com.noname.books_exchange.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserList;
    private int typeList;

    private int idList;

    @OneToMany(mappedBy = "userList")
    private List<UserValueCategory> userValueCategoryList;

    public int getIdUserList() {
        return idUserList;
    }

    public void setIdUserList(int idUserList) {
        this.idUserList = idUserList;
    }

    public int getTypeList() {
        return typeList;
    }

    public void setTypeList(int typeList) {
        this.typeList = typeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserList userList = (UserList) o;

        if (idUserList != userList.idUserList) return false;
        if (typeList != userList.typeList) return false;
        if (idList != userList.idList) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserList;
        result = 31 * result + typeList;
        result = 31 * result + idList;
        return result;
    }
}
