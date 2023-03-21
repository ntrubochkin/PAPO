package com.noname.books_exchange.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noname.books_exchange.model.VerificationInfo;
import com.noname.books_exchange.repository.IVerificationInfoRepo;

@Service
public class VerificationInfoService {
    private final long THIRTY_MINUTES_MS = 1000L * 60L * 30L;

    private final IVerificationInfoRepo repository;

    @Autowired
    public VerificationInfoService(IVerificationInfoRepo repo) {
        repository = repo;
    }

    public void createRow(Integer id, String genStr) {
        VerificationInfo info = new VerificationInfo(id, genStr);
        repository.save(info);
    }

    public VerificationInfo findByGeneratedString(String searchStr) {
        VerificationInfo result = null;
        Optional<VerificationInfo> optional = repository.findVerificationInfoByGeneratedString(searchStr);
        try {
            VerificationInfo info = optional.get();
            long difference = info.getGeneratedAt().getTime() - System.currentTimeMillis();
            if(difference < THIRTY_MINUTES_MS) {
                result = info;
            }
        } catch(NoSuchElementException nsee) {
            //TODO
        }
        return result;
    }

    public void deleteRow(VerificationInfo row) {
        repository.delete(row);
    }
}
