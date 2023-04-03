package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.idParent = ?1")
    List<Category> findCategoriesByIdParent(int id_categories);

    Category findCategoryByIdCategory(int idCategory);
}
