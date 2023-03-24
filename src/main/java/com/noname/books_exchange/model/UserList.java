package com.noname.books_exchange.model;

import jakarta.persistence.*;

@Entity
public class UserList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserList;
    private int typeList;
    private int idList;

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

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
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
