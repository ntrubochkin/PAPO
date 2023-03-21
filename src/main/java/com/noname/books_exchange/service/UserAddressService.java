package com.noname.books_exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noname.books_exchange.model.UserAddress;
import com.noname.books_exchange.repository.IUserAddressRepo;

@Service
public class UserAddressService {
    private final IUserAddressRepo addressRepo;

    @Autowired
    public UserAddressService(IUserAddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public void createAddress(Integer idUser,
                              String city,
                              String street,
                              String buildingNumber,
                              String homeNumber,
                              String apartmentNumber,
                              Integer index)
    {
        String indexStr = (index == null) ? "" : index.toString(); //TODO BRUH
        UserAddress address = new UserAddress(idUser,
                                              indexStr,
                                              city,
                                              street,
                                              homeNumber,
                                              buildingNumber,
                                              apartmentNumber);
        addressRepo.save(address);
    }
}