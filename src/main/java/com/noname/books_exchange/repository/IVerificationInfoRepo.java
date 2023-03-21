package com.noname.books_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noname.books_exchange.model.VerificationInfo;

import java.util.Optional;

@Repository
public interface IVerificationInfoRepo extends JpaRepository<VerificationInfo, Integer> {
    Optional<VerificationInfo> findVerificationInfoByGeneratedString(String searchString);
}