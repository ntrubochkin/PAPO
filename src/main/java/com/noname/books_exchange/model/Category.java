package com.noname.books_exchange.model;

import jakarta.persistence.*;

@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idCategory;
    private String name;
    private Integer idParent;
    private boolean multiSelect;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (idCategory != category.idCategory) return false;
        if (multiSelect != category.multiSelect) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (idParent != null ? !idParent.equals(category.idParent) : category.idParent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idParent != null ? idParent.hashCode() : 0);
        result = 31 * result + (multiSelect ? 1 : 0);
        return result;
    }
}
