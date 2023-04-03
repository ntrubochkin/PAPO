package com.noname.books_exchange.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class UserValueCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserValueCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_list")
    private UserList userList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    private Category category;

    public UserValueCategory() {
    }

    public UserValueCategory(UserList userList, Category category) {
        this.userList = userList;
        this.category = category;
    }

    public int getIdUserValueCategory() {
        return idUserValueCategory;
    }

    public void setIdUserValueCategory(int idUserValueCategory) {
        this.idUserValueCategory = idUserValueCategory;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserValueCategory that = (UserValueCategory) o;

        if (idUserValueCategory != that.idUserValueCategory) return false;
        if (!userList.equals(that.userList)) return false;
        if (!category.equals(that.category)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserValueCategory;
        result = 31 * result + userList.getIdUserList();
        result = 31 * result + category.getIdCategory();
        return result;
    }
}
