package com.example.bookstore.repository.user;

import com.example.bookstore.model.user.Address;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressRepository extends BaseRepository<Address, Long> {

    List<Address> getAddressByUser(User user);
}
