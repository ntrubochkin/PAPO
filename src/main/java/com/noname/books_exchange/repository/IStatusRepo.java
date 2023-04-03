package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepo extends JpaRepository<Status, Integer> {
    @Query("SELECT s FROM Status s WHERE s.idStatus = ?1")
    Status findStatusById(int id);
}
