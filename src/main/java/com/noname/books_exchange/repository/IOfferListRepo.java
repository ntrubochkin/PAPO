package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.OfferList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferListRepo extends JpaRepository<OfferList, Integer> {

}
