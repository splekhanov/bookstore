package com.bookstore.repository.user;

import com.bookstore.repository.BaseRepository;
import com.bookstore.model.user.Address;
import com.bookstore.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressRepository extends BaseRepository<Address, Long> {

    List<Address> getAddressByUser(User user);
}
