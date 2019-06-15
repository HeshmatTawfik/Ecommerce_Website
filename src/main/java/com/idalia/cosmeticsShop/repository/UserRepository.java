package com.idalia.cosmeticsShop.repository;

import com.idalia.cosmeticsShop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);
    User findByPhone(String phone);
    User findById(int id);
}
